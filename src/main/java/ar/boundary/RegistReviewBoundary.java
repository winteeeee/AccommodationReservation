package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.ReservationControl;
import ar.control.ReviewControl;
import ar.entity.Reservation;
import ar.entity.Review;

public class RegistReviewBoundary extends Boundary {
    private ReviewControl reviewControl;
    private ReservationControl reservationControl;
    private Reservation reservation;

    public RegistReviewBoundary(AccommodationReservationApp app, Boundary parent, Reservation reservation) {
        super(app, parent);
        this.reservation = reservation;
        reviewControl = new ReviewControl();
        reservationControl = new ReservationControl();
    }

    @Override
    public void run() {
        System.out.println("==============================");
        System.out.println("리뷰를 등록합니다.");
        System.out.println("==============================");
        System.out.print("별점 입력 : ");
        Byte start = sc.nextByte(); sc.nextLine();
        System.out.print("후기 입력 : ");
        String review = sc.nextLine();

        Review reviewEntity = Review.builder()
                .reservation(reservation)
                .star(start)
                .review(review).build();
        reviewEntity = reviewControl.save(reviewEntity);

        reservation.setReview(reviewEntity);
        reservationControl.save(reservation);
        System.out.println("리뷰 등록이 완료되었습니다.");
        returnToParent();
    }
}
