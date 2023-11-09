package ar.control;

import ar.entity.*;
import com.querydsl.core.types.dsl.BooleanExpression;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
            List<AccommodationDTO> dtoList = new ArrayList<>();
            List<Review> reviewList = query.selectFrom() //TODO 평균 별점 구하기 위한 리뷰 리스트
            List<Accommodation> list = query.selectFrom(qAccommodation)
                    .where(personCheck(person), spaceTypeCheck(spaceType))
                    .orderBy().fetch();
            //TODO orderBy 구현

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
                        .averageStar(0).build();
                dtoList.add(cur);
            });

            return dtoList;
        };
        return transactionStart(fun);
    }
}
