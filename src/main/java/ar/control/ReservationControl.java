package ar.control;

import ar.entity.*;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationControl extends Control<Reservation, Long> {
    private QReservation qReservation = QReservation.reservation;

    private BooleanExpression joinCheck(Long id) {
        return QAccommodation.accommodation.id.eq(id);
    }

    private BooleanExpression betweenCheckByDay(int year, int month, int day) {
        LocalDateTime curDay = LocalDateTime.of(year, month, day, 0, 0);

        return qReservation.dateInfo.startDate.eq(curDay)
                .or(qReservation.dateInfo.endDate.eq(curDay))
                .or(qReservation.dateInfo.startDate.before(curDay).and(qReservation.dateInfo.endDate.after(curDay)));
    }

    private BooleanExpression betweenCheckByMonth(int year, int month) {
        LocalDateTime startDay = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDay = startDay.plusMonths(1).minusSeconds(1);

        return qReservation.dateInfo.endDate.eq(startDay)
                .or(qReservation.dateInfo.endDate.eq(endDay))
                .or(qReservation.dateInfo.endDate.between(startDay, endDay));
    }

    public List<Integer> findSumOfRoomByAccommodationAndYearAndMonth(Accommodation accommodation, int year, int month) {
        Transaction<List<Integer>> fun = (em, query) -> {
            List<Integer> list = new ArrayList<>();
            Calendar cal = Calendar.getInstance();
            cal.set(LocalDate.now().getYear(), month - 1, 1);
            int limitDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

            for (int day = 1; day <= limitDay; day++) {
                Integer cur = query.select(qReservation.room.sum())
                                    .from(qReservation)
                                    .where(joinCheck(accommodation.getId()).and(betweenCheckByDay(year, month, day)))
                                    .fetchOne();
                list.add(cur);
            }

            return list;
        };

        return transactionStart(fun);
    }

    public BigDecimal findSumOfRevenueByAccommodationAndYearAndMonth(Accommodation accommodation, int year, int month) {
        Transaction<BigDecimal> fun = (em, query) -> query.select(qReservation.fare.sum())
                                                        .from(qReservation)
                                                        .where(joinCheck(accommodation.getId()).and(betweenCheckByMonth(year, month)))
                                                        .fetchOne();

        return transactionStart(fun);
    }

    public BooleanExpression memberIdMatching(Member guest) {
        return qReservation.guest.id.eq(guest.getId());
    }

    public List<Reservation> findByMember(Member guest) {
        Transaction<List<Reservation>> fun = (em, query) -> query.selectFrom(qReservation)
                                                                .join(qReservation.accommodation, QAccommodation.accommodation).fetchJoin()
                                                                .leftJoin(qReservation.review, QReview.review1).fetchJoin()
                                                                .where(memberIdMatching(guest))
                                                                .orderBy(qReservation.dateInfo.startDate.asc())
                                                                .fetch();

        return transactionStart(fun);
    }

    public List<Reservation> findTerminatedByMember(Member guest) {
        LocalDateTime now = LocalDateTime.now();
        Transaction<List<Reservation>> fun = (em, query) -> query.selectFrom(qReservation)
                                                                .join(qReservation.accommodation, QAccommodation.accommodation).fetchJoin()
                                                                .leftJoin(qReservation.review, QReview.review1).fetchJoin()
                                                                .where(memberIdMatching(guest).and(qReservation.dateInfo.endDate.before(now)))
                                                                .orderBy(qReservation.dateInfo.startDate.asc())
                                                                .fetch();

        return transactionStart(fun);
    }

    public List<Reservation> findOncomingByMember(Member guest) {
        LocalDateTime now = LocalDateTime.now();
        Transaction<List<Reservation>> fun = (em, query) -> query.selectFrom(qReservation)
                                                                .join(qReservation.accommodation, QAccommodation.accommodation).fetchJoin()
                                                                .leftJoin(qReservation.review, QReview.review1).fetchJoin()
                                                                .where(memberIdMatching(guest).and(qReservation.dateInfo.startDate.after(now)))
                                                                .orderBy(qReservation.dateInfo.startDate.asc())
                                                                .fetch();

        return transactionStart(fun);
    }
}
