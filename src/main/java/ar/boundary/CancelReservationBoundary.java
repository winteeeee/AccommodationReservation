package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.ReservationControl;
import ar.entity.Reservation;
import ar.util.ErrorMessages;

import java.time.LocalDateTime;

public class CancelReservationBoundary extends Boundary {
    private Reservation reservation;
    private ReservationControl reservationControl;

    public CancelReservationBoundary(AccommodationReservationApp app, Boundary parent, Reservation reservation) {
        super(app, parent);
        this.reservation = reservation;
        reservationControl = new ReservationControl();
    }

    @Override
    public void run() {
        if (reservation.getDateInfo().getEndDate().isBefore(LocalDateTime.now())) {
            ErrorMessages.completedReservationError();
        } else {
            sc.nextLine();
            System.out.print("선택한 숙소를 취소하시겠습니까?(Y/N) : ");
            String command = sc.next();

            if (command.equals("Y")) {
                reservationControl.remove(reservation);
                System.out.println("예약이 취소되었습니다.");
            } else if (command.equals("N")) {
            } else {
                ErrorMessages.invalidCommandError();
            }
        }
        returnToParent();
    }
}
