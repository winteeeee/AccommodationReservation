package ar.control;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

    public abstract List<T> findAll();
    public abstract T findById(K id);
}
