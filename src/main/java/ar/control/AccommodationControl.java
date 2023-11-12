package ar.control;

import ar.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AccommodationControl extends Control<Accommodation, Long> {
    private QAccommodation qAccommodation = QAccommodation.accommodation;
    public List<Accommodation> findByMember(Member host) {
        ReturnTransaction<List<Accommodation>> fun = () -> {
            TypedQuery<Accommodation> query = em.createQuery("SELECT a FROM Accommodation a WHERE a.host.id =: hostId", Accommodation.class);
            query.setParameter("hostId", host.getId());
            return query.getResultList();
        };

        return transactionStart(fun);
    }

    private BooleanExpression personCheck(Integer person) {
        if (person == null) {
            return null;
        }

        return QAccommodation.accommodation.accommodatedPerson.goe(person);
    }

    private BooleanExpression spaceTypeCheck(SpaceType spaceType) {
        if (spaceType == null) {
            return null;
        }

        return QAccommodation.accommodation.spaceType.eq(spaceType);
    }

    public List<AccommodationDTO> findByCondition(DateInfo dateInfo, Integer person, SpaceType spaceType) {
        QReservation qReservation = QReservation.reservation;
        QReview qReview = QReview.review1;

        ReturnTransaction<List<AccommodationDTO>> fun = () -> {
            long gap = ChronoUnit.DAYS.between(dateInfo.getStartDate(), dateInfo.getEndDate());
            int weekdayCount = 0;
            int weekendCount = 0;
            for (long i = 0; i < gap; i++) {
                LocalDateTime day = dateInfo.getStartDate().plusDays(i);
                DayOfWeek week = day.getDayOfWeek();

                if (week != DayOfWeek.SATURDAY && week != DayOfWeek.SUNDAY) {
                    weekdayCount++;
                } else {
                    weekendCount++;
                }
            }


            return query.select(Projections.constructor(AccommodationDTO.class,
                    qAccommodation.id,
                    qAccommodation.name,
                    qAccommodation.spaceType,
                    qAccommodation.weekdayFare.multiply(BigDecimal.valueOf(weekdayCount)).add(qAccommodation.weekendFare.multiply(BigDecimal.valueOf(weekendCount))),
                    qReview.star.avg()))
                            .from(qAccommodation)
                            .leftJoin(qAccommodation.reservations, qReservation)
                            .leftJoin(qReservation.review, qReview)
                            .where(personCheck(person), spaceTypeCheck(spaceType))
                            .groupBy(qAccommodation.name)
                            .orderBy(qAccommodation.weekdayFare.multiply(BigDecimal.valueOf(weekdayCount)).add(qAccommodation.weekendFare.multiply(BigDecimal.valueOf(weekendCount))).asc(), qReview.star.avg().asc())
                            .fetch();
            //TODO N + 1 문제 체크
        };
        return transactionStart(fun);
    }
}
