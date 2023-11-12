package ar.control;

import ar.entity.*;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationControl extends Control<Reservation, Long> {
    private QReservation qReservation = QReservation.reservation;

    public BooleanExpression joinCheck(Long id) {
        return QAccommodation.accommodation.id.eq(id);
    }

    public BooleanExpression betweenCheck(int year, int month, int day) {
        LocalDateTime startDay = LocalDateTime.of(year, month, day, 0, 0);
        LocalDateTime endDay = startDay.plusDays(1).minusSeconds(1);

        return QReservation.reservation.dateInfo.startDate.between(startDay, endDay)
                .or(QReservation.reservation.dateInfo.endDate.between(startDay, endDay));
    }

    public List<Integer> findSumOfRoomByAccommodationAndYearAndMonth(Accommodation accommodation, int year, int month) {
        ReturnTransaction<List<Integer>> fun = () -> {
            List<Integer> list = new ArrayList<>();
            QReservation qReservation = QReservation.reservation;
            Calendar cal = Calendar.getInstance();
            cal.set(LocalDate.now().getYear(), month - 1, 1);
            int limitDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

            for (int day = 1; day <= limitDay; day++) {
                Integer cur = query.select(qReservation.room.sum())
                                    .from(qReservation)
                                    .where(joinCheck(accommodation.getId()).and(betweenCheck(year, month, day)))
                                    .fetchOne();
                list.add(cur);
            }

            return list;
        };

        return transactionStart(fun);
    }

    public BooleanExpression memberIdMatching(Member guest) {
        return qReservation.guest.id.eq(guest.getId());
    }

    public List<Reservation> findByMember(Member guest) {
        ReturnTransaction<List<Reservation>> fun = () -> query.selectFrom(qReservation)
                                                                .join(qReservation.accommodation, QAccommodation.accommodation).fetchJoin()
                                                                .where(memberIdMatching(guest))
                                                                .orderBy(qReservation.dateInfo.startDate.asc())
                                                                .fetch();

        return transactionStart(fun);
    }

    public List<Reservation> findTerminatedByMember(Member guest) {
        LocalDateTime now = LocalDateTime.now();
        ReturnTransaction<List<Reservation>> fun = () -> query.selectFrom(qReservation)
                                                                .join(qReservation.accommodation, QAccommodation.accommodation).fetchJoin()
                                                                .where(memberIdMatching(guest).and(qReservation.dateInfo.endDate.before(now)))
                                                                .orderBy(qReservation.dateInfo.startDate.asc())
                                                                .fetch();

        return transactionStart(fun);
    }

    public List<Reservation> findOncomingByMember(Member guest) {
        LocalDateTime now = LocalDateTime.now();
        ReturnTransaction<List<Reservation>> fun = () -> query.selectFrom(qReservation)
                                                                .join(qReservation.accommodation, QAccommodation.accommodation).fetchJoin()
                                                                .where(memberIdMatching(guest).and(qReservation.dateInfo.startDate.after(now)))
                                                                .orderBy(qReservation.dateInfo.startDate.asc())
                                                                .fetch();

        return transactionStart(fun);
    }
}
