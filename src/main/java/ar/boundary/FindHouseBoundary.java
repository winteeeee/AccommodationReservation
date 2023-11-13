package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.AccommodationControl;
import ar.entity.Accommodation;
import ar.entity.AccommodationDTO;
import ar.entity.DateInfo;
import ar.entity.SpaceType;
import ar.util.ErrorMessages;
import ar.util.Keyboard;

import java.time.LocalDateTime;
import java.util.List;

public class FindHouseBoundary extends Boundary{
    private List<AccommodationDTO> list;
    private AccommodationControl accommodationControl;

    public FindHouseBoundary(AccommodationReservationApp app, Boundary parent) {
        super(app, parent);
        list = null;
        this.parent = parent;
        accommodationControl = new AccommodationControl();
    }

    private DateInfo getCheckInOutInfo() {
        System.out.print("체크인 날짜 입력(yyyy-MM-dd) : "); LocalDateTime checkIn = Keyboard.nextLocalDateTime();
        System.out.print("체크아웃 날짜 입력(yyyy-MM-dd) : "); LocalDateTime checkOut = Keyboard.nextLocalDateTime();

        return new DateInfo(checkIn, checkOut);
    }

    private Integer getPerson() {
        System.out.print("인원 입력(null은 0 입력) : "); Integer person = sc.nextInt();
        return person == 0 ? null : person;
    }

    private SpaceType getSpaceType() {
        System.out.print("숙소 유형 입력(0 - 공간 전체, 1 - 개인실, 이외 - null) : "); int spaceTypeIdx = sc.nextInt();
        return spaceTypeIdx != 0 && spaceTypeIdx != 1 ? null : SpaceType.values()[spaceTypeIdx];
    }

    private void showHouseList(List<AccommodationDTO> list) {
        System.out.println("숙소 유형\t\t이름\t\t\t총 가격\t\t평균 별점");
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                AccommodationDTO e = list.get(i);
                System.out.println(i + ". " + e.getSpaceType().name() + "\t" + e.getName() + "\t" + e.getPrice() + "\t" + e.getAverageStar());
            }
        }
    }

    @Override
    public void run() {
        sc.nextLine();

        System.out.println("==============================");
        System.out.println("숙소를 조회합니다.");
        System.out.println("==============================");

        DateInfo dateInfo = getCheckInOutInfo();
        if (dateInfo.getEndDate().isEqual(dateInfo.getStartDate()) || dateInfo.getEndDate().isBefore(dateInfo.getStartDate())) {
            ErrorMessages.inverseEndDate();
            returnToParent();
            return;
        }

        Integer person = getPerson();
        SpaceType spaceType = getSpaceType();
        list = accommodationControl.findByCondition(dateInfo, person, spaceType);
        if (list == null) {
            ErrorMessages.canNotFindHouse();
            returnToParent();
        } else {
            showHouseList(list);

            final int HOUSE_DETAIL = 1;
            final int HOUSE_RESERVE = 2;
            final int RETURN = 3;
            System.out.println("==============================");
            System.out.println("추가 명령");
            System.out.println("1. 숙소 상세 정보 조회");
            System.out.println("2. 숙소 예약");
            System.out.println("3. 돌아가기");
            System.out.println("==============================");
            System.out.print("입력 : "); int command = sc.nextInt();

            if (command == HOUSE_DETAIL || command == HOUSE_RESERVE) {
                System.out.print("숙소 선택 : "); int idx = sc.nextInt();

                if (idx >= list.size()) {
                    ErrorMessages.indexOutOfBound();
                    return;
                }

                Accommodation accommodation = accommodationControl.findById(Accommodation.class, list.get(idx).getId());
                AccommodationDTO accommodationDTO = list.get(idx);
                if (command == HOUSE_DETAIL) {
                    app.setBoundary(new HouseDetailBoundary(app, parent, accommodation));
                } else if (command == HOUSE_RESERVE) {
                    app.setBoundary(new HouseReserveBoundary(app, parent, accommodation, accommodationDTO, dateInfo, person));
                }
            } else if (command == RETURN) {
                returnToParent();
            } else {
                ErrorMessages.invalidCommandError();
            }
        }
    }
}
