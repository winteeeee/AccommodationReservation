package ar.boundary;

import ar.AccommodationReservationApp;
import ar.entity.Reservation;
import ar.util.ErrorMessages;

import java.util.List;

public class ReservationBoundary extends Boundary {
    private List<Reservation> reservations;
    public ReservationBoundary(AccommodationReservationApp app, Boundary parent, List<Reservation> reservations) {
        super(app, parent);
        this.reservations = reservations;
    }

    @Override
    public void run() {
        sc.nextLine();

        final int CANCEL_RESERVATION = 1;
        final int REGIST_REVIEW = 2;
        final int RETURN = 3;
        System.out.println("==============================");
        System.out.println("추가 명령");
        System.out.println("1. 숙소 예약 취소");
        System.out.println("2. 후기 등록");
        System.out.println("3. 돌아가기");
        System.out.println("==============================");
        System.out.print("입력 : "); int command = sc.nextInt();

        if (command == CANCEL_RESERVATION || command == REGIST_REVIEW) {
            System.out.print("예약 선택 : ");
            int idx = sc.nextInt();
            Reservation reservation = reservations.get(idx);

            if (command == CANCEL_RESERVATION) {
                app.setBoundary(new CancelReservationBoundary(app, parent, reservation));
            } else if (command == REGIST_REVIEW) {
                app.setBoundary(new RegistReviewBoundary(app, parent, reservation));
            }
        } else if (command == RETURN) {
            returnToParent();
        } else {
            ErrorMessages.invalidCommandError();
        }
    }
}
