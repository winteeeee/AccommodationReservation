package ar.control;

import ar.entity.Accommodation;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class AccommodationControl extends Control<Accommodation, Long> {
    @Override
    public List<Accommodation> findAll() {
        ReturnTransaction<List<Accommodation>> fun = () -> {
            TypedQuery<Accommodation> query = em.createQuery("SELECT a FROM Accommodation a", Accommodation.class);
            return query.getResultList();
        };

        return transactionStart(fun);
    }

    @Override
    public Accommodation findById(Long id) {
        ReturnTransaction<List<Accommodation>> fun = () -> {
            List<Accommodation> result = new ArrayList<>();
            result.add(em.find(Accommodation.class, id));
            return result;
        };

        return transactionStart(fun).get(0);
    }
}
