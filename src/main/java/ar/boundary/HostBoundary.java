package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.AccommodationControl;
import ar.control.HostControl;
import ar.entity.Accommodation;
import ar.entity.Host;
import ar.util.ErrorMessages;
import ar.util.Keyboard;

import java.math.BigDecimal;
import java.util.Scanner;

public class HostBoundary implements Boundary {
    private Host host;
    private Scanner sc = Keyboard.getInstance();
    private HostControl hostControl;
    private AccommodationControl accommodationControl;

    public HostBoundary() {
        host = null;
        hostControl = new HostControl();
        accommodationControl = new AccommodationControl();
    }

    private Host login() {
        Host result = null;

        while (result == null) {
            System.out.println("==============================");
            System.out.println("호스트로 로그인합니다.");
            System.out.println("==============================");
            System.out.print("아이디 입력 : ");
            String id = sc.next();
            System.out.print("비밀번호 입력 : ");
            String password = sc.next();

            result = hostControl.login(id, password);
            if (result == null) {
                ErrorMessages.loginFailed();
            }
        }

        return result;
    }

    private void registHouse(Host host) {
        sc.nextLine();
        System.out.println("==============================");
        System.out.println("숙소를 등록합니다.");
        System.out.println("==============================");
        System.out.print("숙소 이름 입력 : ");
        String name = sc.nextLine();
        System.out.print("숙소 주소 입력 : ");
        String address = sc.nextLine();
        System.out.print("수용 인원 입력 : ");
        Integer accommodatedPerson = sc.nextInt();
        System.out.print("방 개수 입력 : ");
        Integer room = sc.nextInt();
        System.out.print("침실 개수 입력 : ");
        Integer bedroom = sc.nextInt();
        System.out.print("욕실 개수 입력 : ");
        Integer bathroom = sc.nextInt(); sc.nextLine();
        System.out.print("숙소 소개 입력 : ");
        String introduction = sc.nextLine();
        System.out.print("평일 요금 입력 : ");
        BigDecimal weekdayFare = sc.nextBigDecimal();
        System.out.print("주말 요금 입력 : ");
        BigDecimal weekendFare = sc.nextBigDecimal();

        Accommodation house = Accommodation.builder()
                .name(name)
                .address(address)
                .host(host)
                .accommodatedPerson(accommodatedPerson)
                .room(room)
                .bedroom(bedroom)
                .bed(bedroom)
                .bathroom(bathroom)
                .introduction(introduction)
                .weekdayFare(weekdayFare)
                .weekendFare(weekendFare).build();

        accommodationControl.save(house);
        System.out.println("숙소가 성공적으로 등록되었습니다!");
    }

    @Override
    public void run(AccommodationReservationApp app) {
        final int REGIST_HOUSE = 1;
        final int SET_DISCOUNT_POLICY = 2;
        final int SHOW_REVENUE = 3;
        final int LOGOUT = 4;

        if (host == null) {
            host = login();
        }

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
            registHouse(host);
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
