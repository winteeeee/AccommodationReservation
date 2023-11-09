package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.MemberControl;
import ar.entity.Member;
import ar.entity.RoleType;
import ar.util.ErrorMessages;
import ar.util.Keyboard;

import java.util.Scanner;

public class MainBoundary extends Boundary {
    private MemberControl memberControl;

    public MainBoundary(AccommodationReservationApp app) {
        super(app, null);
        memberControl = new MemberControl();
    }

    private Member login() {
        Member result = null;

        while (result == null) {
            System.out.println("==============================");
            System.out.println("호스트로 로그인합니다.");
            System.out.println("==============================");
            System.out.print("아이디 입력 : ");
            String id = sc.next();
            System.out.print("비밀번호 입력 : ");
            String password = sc.next();

            result = memberControl.login(id, password);
            if (result == null) {
                ErrorMessages.loginFailed();
            }
        }

        return result;
    }

    @Override
    public void run() {
        final int LOGIN = 1;
        final int QUIT = 2;

        System.out.println("==============================");
        System.out.println("환영합니다. 숙소 예약 시스템입니다.");
        System.out.println("1. 로그인");
        System.out.println("2. 종료");
        System.out.println("==============================");
        System.out.print("입력 : ");

        int command = sc.nextInt();
        if (command == LOGIN) {
            Member member = login();
            if (member.getRoleType().equals(RoleType.HOST)) {
                app.setBoundary(new HostBoundary(app, this, member));
            } else if (member.getRoleType().equals(RoleType.GUEST)) {
                app.setBoundary(new GuestBoundary(app, this, member));
            }
        } else if (command == QUIT) {
            System.exit(0);
        } else {
            ErrorMessages.invalidCommandError();
        }
    }
}
