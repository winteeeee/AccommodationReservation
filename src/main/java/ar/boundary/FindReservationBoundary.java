package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.ReservationControl;
import ar.entity.Reservation;
import ar.util.ErrorMessages;

import java.util.List;

public class FindReservationBoundary extends Boundary {
    private ReservationControl reservationControl;

    public FindReservationBoundary(AccommodationReservationApp app, Boundary parent) {
        super(app, parent);
        reservationControl = new ReservationControl();
    }

    private void showReservationList(List<Reservation> list) {
        System.out.println("숙소명\t\t체크인\t\t\t체크아웃\t\t요금\t\t후기");
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
               Reservation e = list.get(i);
               String reviewString = e.getReview() == null ? "X" : "O";

               System.out.println(i + ". " +
                       e.getAccommodation().getName() + "\t" +
                       e.getDateInfo().getStartDate() + "\t" +
                       e.getDateInfo().getEndDate() + "\t" +
                       e.getFare() + "\t" +
                       reviewString);
            }
        }
    }

    @Override
    public void run() {
        sc.nextLine();

        final int FIND_ALL = 1;
        final int FIND_CHECKOUT_COMPLETE = 2;
        final int FIND_CHECKIN_SCHEDULED = 3;
        final int RETURN = 4;
        System.out.println("==============================");
        System.out.println("예약을 조회합니다.");
        System.out.println("1. 전체 조회");
        System.out.println("2. 체크아웃 완료 예약 조회");
        System.out.println("3. 체크인 예정 예약 조회");
        System.out.println("4. 돌아가기");
        System.out.println("==============================");
        System.out.print("입력 : ");

        int command = sc.nextInt();
        if (command == FIND_ALL || command == FIND_CHECKOUT_COMPLETE || command == FIND_CHECKIN_SCHEDULED) {
            List<Reservation> list;
            if (command == FIND_ALL) {
                list = reservationControl.findByMember(app.getSignedMember());
            } else if (command == FIND_CHECKOUT_COMPLETE) {
                list = reservationControl.findTerminatedByMember(app.getSignedMember());
            } else {
                list = reservationControl.findOncomingByMember(app.getSignedMember());
            }

            showReservationList(list);
        } else if (command == RETURN) {
            returnToParent();
        } else {
            ErrorMessages.invalidCommandError();
        }
    }
}
