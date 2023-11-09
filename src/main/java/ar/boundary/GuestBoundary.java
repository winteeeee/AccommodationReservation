package ar.boundary;

import ar.AccommodationReservationApp;
import ar.entity.Member;
import ar.util.ErrorMessages;

public class GuestBoundary extends Boundary{
    private Member guest;

    public GuestBoundary(AccommodationReservationApp app, Boundary parent, Member guest) {
        super(app, parent);
        this.parent = parent;
        this.guest = guest;
    }

    @Override
    public void run() {
        final int FIND_HOUSE = 1;
        final int FIND_RESERVATION = 2;
        final int LOGOUT = 3;

        System.out.println("==============================");
        System.out.println(guest.getName() + " 게스트님 환영합니다.");
        System.out.println("1. 숙소 조회");
        System.out.println("2. 예약 조회");
        System.out.println("3. 로그아웃");
        System.out.println("==============================");
        System.out.print("입력 : ");

        int command = sc.nextInt();
        if (command == FIND_HOUSE) {
            app.setBoundary(new FindHouseBoundary(app, this));
        } else if (command == FIND_RESERVATION) {
            //TODO 구현
        } else if (command == LOGOUT) {
            returnToParent();
        } else {
            ErrorMessages.invalidCommandError();
        }
    }
}
