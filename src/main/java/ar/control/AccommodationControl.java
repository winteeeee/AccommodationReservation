package ar.control;

import ar.entity.*;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AccommodationControl extends Control<Accommodation, Long> {
    private QAccommodation qAccommodation = QAccommodation.accommodation;
    public List<Accommodation> findByMember(Member host) {
        Transaction<List<Accommodation>> fun = (em, query) -> {
            TypedQuery<Accommodation> typedQuery = em.createQuery("SELECT a FROM Accommodation a WHERE a.host.id =: hostId", Accommodation.class);
            typedQuery.setParameter("hostId", host.getId());
            return typedQuery.getResultList();
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
        QDiscountPolicy qDiscountPolicy = QDiscountPolicy.discountPolicy;

        Transaction<List<AccommodationDTO>> fun = (em, query) -> {
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

            NumberExpression<BigDecimal> originalPrice = qAccommodation.weekdayFare.multiply(BigDecimal.valueOf(weekdayCount)).add(qAccommodation.weekendFare.multiply(BigDecimal.valueOf(weekendCount)));
            NumberExpression<BigDecimal> quantitativePrice = originalPrice.subtract(qAccommodation.discountPolicy.quantitativeDiscount);
            NumberExpression<BigDecimal> fixedRatePrice = originalPrice.subtract(originalPrice.multiply(qAccommodation.discountPolicy.fixedRateDiscount));
            BooleanExpression originalCondition = qAccommodation.discountPolicy.dateInfo.startDate.after(dateInfo.getEndDate())
                                                    .or(qAccommodation.discountPolicy.dateInfo.endDate.before(dateInfo.getStartDate()));

            NumberExpression<BigDecimal> priceCase = new CaseBuilder()
                    .when(originalCondition.not().and(qAccommodation.discountPolicy.isQuantitativeDiscount.isTrue())).then(quantitativePrice)
                    .when(originalCondition.not().and(qAccommodation.discountPolicy.isQuantitativeDiscount.isFalse())).then(fixedRatePrice)
                    .otherwise(originalPrice);


            return query.select(Projections.constructor(AccommodationDTO.class,
                    qAccommodation.id,
                    qAccommodation.name,
                    qAccommodation.spaceType,
                    priceCase,
                    qReview.star.avg()))
                            .from(qAccommodation)
                            .leftJoin(qAccommodation.discountPolicy, qDiscountPolicy)
                            .leftJoin(qAccommodation.reservations, qReservation)
                            .leftJoin(qReservation.review, qReview)
                            .where(personCheck(person), spaceTypeCheck(spaceType))
                            .groupBy(qAccommodation.name)
                            .orderBy(priceCase.asc(), qReview.star.avg().asc())
                            .fetch();
        };
        return transactionStart(fun);
    }
}
