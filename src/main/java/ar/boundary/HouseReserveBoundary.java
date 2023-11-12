package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.ReservationControl;
import ar.entity.*;
import ar.util.Keyboard;

import java.time.LocalDateTime;

public class HouseReserveBoundary extends Boundary {
    private Accommodation accommodation;
    private AccommodationDTO accommodationDTO;
    private DateInfo checkInOutInfo;
    private ReservationControl reservationControl;

    public HouseReserveBoundary(AccommodationReservationApp app, Boundary parent, Accommodation accommodation, AccommodationDTO accommodationDTO, DateInfo dateInfo) {
        super(app, parent);
        this.accommodation = accommodation;
        this.accommodationDTO = accommodationDTO;
        checkInOutInfo = dateInfo;
        reservationControl = new ReservationControl();
    }

    @Override
    public void run() {
        sc.nextLine();

        System.out.println("==============================");
        System.out.println("숙소를 예약합니다.");
        System.out.println("==============================");
        System.out.print("숙소 이용 인원 입력 : ");
        Integer person = sc.nextInt();

        Integer room = 0;
        if (accommodation.getSpaceType() == SpaceType.ENTIRE_PLACE) {
            System.out.println("주어진 숙소는 공간 전체 대여이므로 모든 방이 예약됩니다.");
            room = accommodation.getRoom();
        } else if (accommodation.getSpaceType() == SpaceType.PRIVATE_ROOM) {
            System.out.print("몇 개의 방을 이용하시겠습니까? : ");
            room = sc.nextInt();
        }

        Reservation reservation = Reservation.builder()
                                            .guest(app.getSignedMember())
                                            .accommodation(accommodation)
                                            .review(null)
                                            .dateInfo(checkInOutInfo)
                                            .person(person)
                                            .room(room)
                                            .fare(accommodationDTO.getPrice()).build();
        reservationControl.save(reservation);
        System.out.println("숙소 예약이 완료되었습니다!");
        returnToParent();
    }
}