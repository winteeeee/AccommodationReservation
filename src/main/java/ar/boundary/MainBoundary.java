package ar.boundary;

import ar.AccommodationReservationApp;
import ar.util.ErrorMessages;
import ar.util.Keyboard;

import java.util.Scanner;

public class MainBoundary implements Boundary {
    private Scanner sc = Keyboard.getInstance();

    @Override
    public void run(AccommodationReservationApp app) {
        final int HOST_LOGIN = 1;
        final int GUEST_LOGIN = 2;
        final int QUIT = 3;

        System.out.println("==============================");
        System.out.println("환영합니다. 숙소 예약 시스템입니다.");
        System.out.println("1. 호스트 로그인");
        System.out.println("2. 게스트 로그인");
        System.out.println("3. 종료");
        System.out.println("==============================");
        System.out.print("입력 : ");

        int command = sc.nextInt();
        if (command == HOST_LOGIN) {
            app.setBoundary(new HostBoundary());
        } else if (command == GUEST_LOGIN) {
            app.setBoundary(new GuestBoundary());
        } else if (command == QUIT) {
            System.exit(0);
        } else {
            ErrorMessages.invalidCommandError();
        }
    }
}
