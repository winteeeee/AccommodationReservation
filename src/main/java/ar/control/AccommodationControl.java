package ar.control;

import ar.entity.Accommodation;
import ar.entity.Host;

import javax.persistence.TypedQuery;
import java.util.List;

public class AccommodationControl extends Control<Accommodation, Long> {
    public List<Accommodation> findByHost(Host host) {
        ReturnTransaction<List<Accommodation>> fun = () -> {
            TypedQuery<Accommodation> query = em.createQuery("SELECT a FROM Accommodation a WHERE a.host.id =: hostId", Accommodation.class);
            query.setParameter("hostId", host.getId());
            return query.getResultList();
        };

        return transactionStart(fun);
    }
}
