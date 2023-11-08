package ar.control;

import ar.entity.Accommodation;
import ar.entity.DiscountPolicy;

import javax.persistence.TypedQuery;
import java.util.List;

public class DiscountPolicyControl extends Control<DiscountPolicy, Long> {
    public DiscountPolicy findByAccommodation(Accommodation accommodation) {
        ReturnTransaction<List<DiscountPolicy>> fun = () -> {
            TypedQuery<DiscountPolicy> query = em.createQuery("SELECT d FROM DiscountPolicy d WHERE d.accommodation.id =: accommodationId", DiscountPolicy.class);
            query.setParameter("accommodationId", accommodation.getId());
            return query.getResultList();
        };

        List<DiscountPolicy> list = transactionStart(fun);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
