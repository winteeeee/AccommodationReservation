package ar.control;

import ar.entity.QReservation;
import ar.entity.QReview;
import ar.entity.Reservation;
import ar.entity.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewControl extends Control<Review, Long> {
    private QReview qReview = QReview.review1;
    public List<Review> findByReservations(List<Reservation> reservations) {
        QReservation qReservation = QReservation.reservation;

        ReturnTransaction<List<Review>> fun = () -> {
            List<Review> reviews = new ArrayList<>();
            reservations.forEach((reservation) -> {
                Review cur = query.selectFrom(qReview)
                                    .from(qReservation)
                                    .join(qReservation.review, qReview)
                                    .fetchOne();
                reviews.add(cur);
            });

            return reviews;
        };

        return transactionStart(fun);
    }
}
