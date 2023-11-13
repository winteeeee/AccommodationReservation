package ar.control;

import ar.entity.Entity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Control<T extends Entity, K> {
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ar");

    protected <R> R transactionStart(Transaction<R> fun) {
        EntityManager em = emf.createEntityManager();
        JPAQueryFactory query = new JPAQueryFactory(em);
        EntityTransaction tx = em.getTransaction();
        R result = null;

        try {
            tx.begin();
            result = fun.execute(em, query);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        return result;
    }

    public T save(T entity) {
        Transaction<T> fun = (em, query) -> {
            entity.setCreatedDate(LocalDateTime.now());
            return em.merge(entity);
        };

        return transactionStart(fun);
    }

    public List<T> save(List<T> entities) {
        Transaction<List<T>> fun = (em, query) -> {
            List<T> list = new ArrayList<>();
                entities.forEach((e) -> {
                    e.setCreatedDate(LocalDateTime.now());
                    list.add(em.merge(e));
            });

            return list;
        };

        return transactionStart(fun);
    }

    public T remove(T entity) {
        return transactionStart((em, query) -> {
            em.remove(em.merge(entity));
            return null;
        });
    }

    public T remove(List<T> entities) {
        return transactionStart((em, query) -> {
                    entities.forEach((e) -> em.remove(em.merge(e)));
                    return null;
        });
    }

    public List<T> findAll(Class<T> c) {
        Transaction<List<T>> fun = (em, query) -> {
            TypedQuery<T> typedQuery = em.createQuery("SELECT a FROM " + c.getName() + " a", c);
            return typedQuery.getResultList();
        };

        return transactionStart(fun);
    }
    public T findById(Class<T> c, K id) {
        Transaction<T> fun = (em, query) -> em.find(c, id);
        return transactionStart(fun);
    }
}
