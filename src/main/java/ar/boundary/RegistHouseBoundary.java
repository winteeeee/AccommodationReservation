package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.AccommodationControl;
import ar.entity.*;
import ar.util.Keyboard;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class RegistHouseBoundary extends Boundary implements Returnable {
    private Boundary parent;
    private Member host;
    private Scanner sc = Keyboard.getInstance();
    private AccommodationControl accommodationControl;

    public RegistHouseBoundary(AccommodationReservationApp app, Boundary parent, Member host) {
        super(app);
        this.parent = parent;
        this.host = host;
        accommodationControl = new AccommodationControl();
    }

    @Override
    public void run() {
        sc.nextLine();
        System.out.println("==============================");
        System.out.println("숙소를 등록합니다.");
        System.out.println("==============================");
        System.out.print("숙소 이름 입력 : ");
        String name = sc.nextLine();
        System.out.print("숙소 주소 입력 : ");
        String address = sc.nextLine();
        System.out.print("숙소 유형 입력(0 - 공간 전체, 1 - 개인실) : ");
        Integer spaceType = sc.nextInt();
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
        TimeInfo timeInfo = new TimeInfo(LocalTime.of(15, 0), LocalTime.of(11, 0));

        Accommodation house = Accommodation.builder()
                .spaceType(SpaceType.values()[spaceType])
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
                .weekendFare(weekendFare)
                .timeInfo(timeInfo).build();
        house = accommodationControl.save(house);
        app.setBoundary(new AmenitiesBoundary(app, parent, house));
    }

    @Override
    public void returnToParent() {
        app.setBoundary(parent);
    }
}
