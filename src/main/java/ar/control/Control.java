package ar.control;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public abstract class Control<T, K> {
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ar");
    protected static EntityManager em;
    protected static JPAQueryFactory query;

    protected void transactionStart(VoidTransaction fun) {
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            fun.execute();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    protected <R> R transactionStart(ReturnTransaction<R> fun) {
        em = emf.createEntityManager();
        query = new JPAQueryFactory(em);
        EntityTransaction tx = em.getTransaction();
        R result = null;

        try {
            tx.begin();
            result = fun.execute();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        return result;
    }

    public T save(T entity) {
        ReturnTransaction<T> fun = () -> em.merge(entity);
        return transactionStart(fun);
    }

    public List<T> save(List<T> entities) {
        ReturnTransaction<List<T>> fun = () -> {
            List<T> list = new ArrayList<>();
            entities.forEach((e) -> {
                list.add(em.merge(e));
            });

            return list;
        };

        return transactionStart(fun);
    }

    public void remove(T entity) {
        transactionStart(() -> {
            em.remove(em.merge(entity));
        });
    }

    public void remove(List<T> entities) {
        transactionStart(() -> {
            entities.forEach((e) -> em.remove(em.merge(e)));
        });
    }

    public List<T> findAll(Class<T> c) {
        ReturnTransaction<List<T>> fun = () -> {
            TypedQuery<T> query = em.createQuery("SELECT a FROM " + c.getName() + " a", c);
            return query.getResultList();
        };

        return transactionStart(fun);
    }
    public T findById(Class<T> c, K id) {
        ReturnTransaction<T> fun = () -> em.find(c, id);
        return transactionStart(fun);
    }
}
