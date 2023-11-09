package ar.control;

import ar.entity.Accommodation;
import ar.entity.Member;
import ar.entity.SpaceType;
import ar.entity.TimeInfo;

import javax.persistence.TypedQuery;
import java.util.List;

public class AccommodationControl extends Control<Accommodation, Long> {
    public List<Accommodation> findByMember(Member host) {
        ReturnTransaction<List<Accommodation>> fun = () -> {
            TypedQuery<Accommodation> query = em.createQuery("SELECT a FROM Accommodation a WHERE a.host.id =: hostId", Accommodation.class);
            query.setParameter("hostId", host.getId());
            return query.getResultList();
        };

        return transactionStart(fun);
    }

    public List<Accommodation> findByCondition(TimeInfo timeInfo, Integer person, SpaceType spaceType) {
        //TODO QueryDSL, booleanexpression, ORDER BY 사용하여 구현
        //TODO 정렬 기준도 인수로 받아오게끔 구현
        return null;
    }
}
