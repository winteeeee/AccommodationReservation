package ar.control;

import ar.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccommodationControl extends Control<Accommodation, Long> {
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
        QAccommodation qAccommodation = QAccommodation.accommodation;
        QReview qReview = QReview.review1;

        ReturnTransaction<List<AccommodationDTO>> fun = () -> {
            List<AccommodationDTO> list = query.select(Projections.constructor(AccommodationDTO.class,
                    qAccommodation.name,
                    qAccommodation.spaceType,
                    qAccommodation.weekdayFare,
                    qAccommodation.weekendFare,
                    qReview.star.avg()))
                            .from(qAccommodation, qReview)
                            .where(qAccommodation.id.eq(qReview.reservation.accommodation.id).and(personCheck(person)).and(spaceTypeCheck(spaceType)))
                            .orderBy(qAccommodation.weekdayFare.asc(), qAccommodation.weekendFare.asc(), qReview.star.avg().asc())
                            .fetch();

            list.forEach((e) -> {
                long gap = ChronoUnit.DAYS.between(dateInfo.getStartDate(), dateInfo.getEndDate());
                BigDecimal price = BigDecimal.ZERO;
                for (long i = 0; i < gap; i++) {
                    LocalDateTime day = dateInfo.getStartDate().plusDays(i);
                    DayOfWeek week = day.getDayOfWeek();

                    if (week != DayOfWeek.SATURDAY && week != DayOfWeek.SUNDAY) {
                        price = price.add(e.getWeekdayFare());
                    } else {
                        price = price.add(e.getWeekendFare());
                    }
                }

                AccommodationDTO cur = AccommodationDTO.builder()
                        .name(e.getName())
                        .spaceType(e.getSpaceType())
                        .price(price)
                        .averageStar(avgStar).build();
                dtoList.add(cur);
            });

            return dtoList;
        };
        return transactionStart(fun);
    }
}
