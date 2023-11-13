package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.ReservationControl;
import ar.entity.Accommodation;
import ar.util.Printer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ShowRevenueBoundary extends Boundary {
    private Accommodation accommodation;
    private ReservationControl reservationControl;

    public ShowRevenueBoundary(AccommodationReservationApp app, Boundary parent, Accommodation accommodation) {
        super(app, parent);
        this.accommodation = accommodation;
        reservationControl = new ReservationControl();
    }

    @Override
    public void run() {
        System.out.println("==============================");
        System.out.println("예약 현황을 확인합니다.");
        int month = LocalDate.now().getMonth().getValue();
        while (1 <= month && month <= 12) {
            List<Integer> roomCount = reservationControl.findSumOfRoomByAccommodationAndYearAndMonth(accommodation, LocalDate.now().getYear(), month);
            BigDecimal revenue = reservationControl.findSumOfRevenueByAccommodationAndYearAndMonth(accommodation, LocalDate.now().getYear(), month);
            Printer.showReservationStatus(accommodation, month, roomCount);
            System.out.println(LocalDate.now().getYear() + "년 " + month + "월의 총 매출 : " + revenue);
            System.out.print("추가 예약 현황을 볼 달 입력(1 ~ 12 이외의 값 입력 시 종료) : "); month = sc.nextInt();
        }
        System.out.println("==============================");
        returnToParent();
    }
}
