package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.AccommodationControl;
import ar.entity.AccommodationDTO;
import ar.entity.DateInfo;
import ar.entity.SpaceType;
import ar.util.ErrorMessages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FindHouseBoundary extends Boundary{
    private AccommodationControl accommodationControl;

    public FindHouseBoundary(AccommodationReservationApp app, Boundary parent) {
        super(app, parent);
        this.parent = parent;
        accommodationControl = new AccommodationControl();
    }

    private List<AccommodationDTO> findHouse() {
        sc.nextLine();
        String defaultTime = " 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("==============================");
        System.out.println("숙소를 조회합니다.");
        System.out.println("==============================");

        System.out.print("체크인 날짜 입력(yyyy-MM-dd) : ");
        String checkIn = sc.nextLine() + defaultTime;

        System.out.print("체크아웃 날짜 입력(yyyy-MM-dd) : ");
        String checkOut = sc.nextLine() + defaultTime;
        DateInfo dateInfo = new DateInfo(LocalDateTime.parse(checkIn, formatter), LocalDateTime.parse(checkOut, formatter));

        System.out.print("인원 입력(null은 0 입력) : ");
        Integer person = sc.nextInt();
        person = person == 0 ? null : person;

        System.out.print("숙소 유형 입력(0 - 공간 전체, 1 - 개인실, 이외 - null) : ");
        int spaceTypeIdx = sc.nextInt();
        SpaceType spaceType = spaceTypeIdx != 0 && spaceTypeIdx != 1 ? null : SpaceType.values()[spaceTypeIdx];

        List<AccommodationDTO> list = accommodationControl.findByCondition(dateInfo, person, spaceType);
        System.out.println("숙소 유형\t이름\t총 가격\t평균 별점");
        list.forEach((e) -> {
            System.out.println(e.getSpaceType().name() + "\t" + e.getName() + "\t" + e.getPrice() + "\t" + e.getAverageStar());
        });

        return list;
    }

    @Override
    public void run() {
        List<AccommodationDTO> list = findHouse();

        final int HOUSE_DETAIL = 1;
        final int HOUSE_RESERVE = 2;
        final int RETURN = 3;
        System.out.println("==============================");
        System.out.println("추가 명령");
        System.out.println("1. 숙소 상세 정보 조회");
        System.out.println("2. 숙소 예약");
        System.out.println("3. 돌아가기");
        System.out.println("==============================");
        System.out.print("입력 : ");

        int command = sc.nextInt();
        if (command == HOUSE_DETAIL) {
            //TODO 구현
        } else if (command == HOUSE_RESERVE) {
            //TODO 구현
        } else if (command == RETURN) {
            returnToParent();
        } else {
            ErrorMessages.invalidCommandError();
        }
    }
}
