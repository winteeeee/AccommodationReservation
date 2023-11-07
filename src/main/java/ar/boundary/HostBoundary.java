package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.HostControl;
import ar.entity.Host;
import ar.util.ErrorMessages;
import ar.util.Keyboard;

import java.util.Scanner;

public class HostBoundary implements Boundary {
    private Scanner sc = Keyboard.getInstance();
    private HostControl hostControl;

    public HostBoundary() {
        hostControl = new HostControl();
    }

    private Host login() {
        Host result = null;

        while (result == null) {
            System.out.println("==============================");
            System.out.println("호스트로 로그인합니다.");
            System.out.println("==============================");
            System.out.print("아이디 입력 : ");
            String id = sc.nextLine();
            System.out.print("비밀번호 입력 : ");
            String password = sc.nextLine();

            result = hostControl.login(id, password);
            if (result == null) {
                ErrorMessages.loginFailed();
            }
        }

        return result;
    }

    @Override
    public void run(AccommodationReservationApp app) {
        final int REGIST_HOUSE = 1;
        final int SET_DISCOUNT_POLICY = 2;
        final int SHOW_REVENUE = 3;
        final int LOGOUT = 4;

        Host host = login();

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
            // TODO 구현
        } else if (command == SET_DISCOUNT_POLICY) {
            // TODO 구현
        } else if (command == SHOW_REVENUE) {
            // TODO 구현
        } else if (command == LOGOUT) {
            app.setBoundary(new MainBoundary());
        } else {
            ErrorMessages.invalidCommandError();
        }
    }
}
