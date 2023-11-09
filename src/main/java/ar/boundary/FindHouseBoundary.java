package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.AccommodationControl;
import ar.entity.SpaceType;
import ar.entity.TimeInfo;

import java.time.LocalTime;

public class FindHouseBoundary extends Boundary implements Returnable {
    private Boundary parent;
    private AccommodationControl accommodationControl;

    public FindHouseBoundary(AccommodationReservationApp app, Boundary parent) {
        super(app);
        this.parent = parent;
        accommodationControl = new AccommodationControl();
    }

    @Override
    public void run() {
        sc.nextLine();

        System.out.println("==============================");
        System.out.println("숙소를 조회합니다.");
        System.out.println("==============================");

        System.out.print("체크인 시간 입력(시) : ");
        int checkInHour = sc.nextInt();
        System.out.print("체크인 시간 입력(분) : ");
        int checkInMinute = sc.nextInt();
        LocalTime checkIn = LocalTime.of(checkInHour, checkInMinute);

        System.out.print("체크아웃 시간 입력(시) : ");
        int checkOutHour = sc.nextInt();
        System.out.print("체크아웃 시간 입력(분) : ");
        int checkOutMinute = sc.nextInt();
        LocalTime checkOut = LocalTime.of(checkOutHour, checkOutMinute);
        TimeInfo timeInfo = new TimeInfo(checkIn, checkOut);

        System.out.print("인원 입력 : ");
        Integer person = sc.nextInt();

        System.out.print("숙소 유형 입력(0 - 공간 전체, 1 - 개인실) : ");
        SpaceType spaceType = SpaceType.values()[sc.nextInt()];

        accommodationControl.findByCondition(timeInfo, person, spaceType);
    }

    @Override
    public void returnToParent() {
        app.setBoundary(parent);
    }
}
