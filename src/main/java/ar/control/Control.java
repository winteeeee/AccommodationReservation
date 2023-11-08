package ar.control;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Control<T, K> {
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ar");
    protected EntityManager em;

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

    protected List<T> transactionStart(ReturnTransaction<List<T>> fun) {
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<T> result = null;

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

    public void save(T entity) {
        transactionStart(() -> {
            em.persist(entity);
        });
    }

    public void save(List<T> entities) {
        transactionStart(() -> {
            entities.forEach((e) -> em.persist(e));
        });
    }

    public void remove(T entity) {
        transactionStart(() -> {
            em.remove(entity);
        });
    }

    public void remove(List<T> entities) {
        transactionStart(() -> {
            entities.forEach((e) -> em.remove(e));
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
        ReturnTransaction<List<T>> fun = () -> {
            List<T> result = new ArrayList<>();
            result.add(em.find(c, id));
            return result;
        };

        return transactionStart(fun).get(0);
    }
}
