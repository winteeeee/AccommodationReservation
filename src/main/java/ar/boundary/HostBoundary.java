package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.*;
import ar.entity.*;
import ar.util.ErrorMessages;
import ar.util.Keyboard;

import java.util.List;
import java.util.Scanner;

public class HostBoundary extends Boundary implements Returnable {
    private Boundary parent;
    private Member host;
    private AccommodationControl accommodationControl;

    public HostBoundary(AccommodationReservationApp app, Boundary parent, Member host) {
        super(app);
        this.parent = parent;
        this.host = host;
        accommodationControl = new AccommodationControl();
    }

    private Accommodation findAccommodation() {
        List<Accommodation> list = accommodationControl.findByMember(host);
        System.out.println("[숙소 선택]");

        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + " : " + list.get(i - 1).getName());
        }
        System.out.print("입력 : ");
        int idx = sc.nextInt() - 1;

        return list.get(idx);
    }

    @Override
    public void run() {
        final int REGIST_HOUSE = 1;
        final int SET_DISCOUNT_POLICY = 2;
        final int SHOW_REVENUE = 3;
        final int LOGOUT = 4;

        System.out.println("==============================");
        System.out.println(host.getName() + " 호스트님 환영합니다.");
        System.out.println("1. 숙소 등록");
        System.out.println("2. 할인 정책 수정");
        System.out.println("3. 매출 확인");
        System.out.println("4. 로그아웃");
        System.out.println("==============================");
        System.out.print("입력 : ");

        int command = sc.nextInt();
        if (command == REGIST_HOUSE) {
            app.setBoundary(new RegistHouseBoundary(app, this, host));
        } else if (command == SET_DISCOUNT_POLICY) {
            Accommodation accommodation = findAccommodation();
            app.setBoundary(new ApplyDiscountPolicyBoundary(app, this, accommodation));
        } else if (command == SHOW_REVENUE) {
            Accommodation accommodation = findAccommodation();
            // TODO 구현
        } else if (command == LOGOUT) {
            returnToParent();
        } else {
            ErrorMessages.invalidCommandError();
        }
    }

    @Override
    public void returnToParent() {
        app.setBoundary(parent);
    }
}
