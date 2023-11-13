package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.AccommodationControl;
import ar.control.DiscountPolicyControl;
import ar.entity.Accommodation;
import ar.entity.DateInfo;
import ar.entity.DiscountPolicy;
import ar.util.ErrorMessages;
import ar.util.Keyboard;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ApplyDiscountPolicyBoundary extends Boundary {
    private Accommodation accommodation;
    private AccommodationControl accommodationControl;
    private DiscountPolicyControl discountPolicyControl;


    public ApplyDiscountPolicyBoundary(AccommodationReservationApp app, Boundary parent, Accommodation accommodation) {
        super(app, parent);
        this.parent = parent;
        this.accommodation = accommodation;
        accommodationControl = new AccommodationControl();
        discountPolicyControl = new DiscountPolicyControl();
    }

    private void calPrice() {
        System.out.println("==============================");
        DiscountPolicy discountPolicy = discountPolicyControl.findByAccommodation(accommodation);
        if (discountPolicy != null && discountPolicy.getDateInfo().getStartDate().isBefore(LocalDateTime.now()) &&
                                      discountPolicy.getDateInfo().getEndDate().isAfter(LocalDateTime.now())) {
            System.out.println("숙소에 할인이 적용 중입니다.");
            BigDecimal discountWeekdayFare;
            BigDecimal discountWeekendFare;

            if (discountPolicy.getIsQuantitativeDiscount()) {
                discountWeekdayFare = accommodation.getWeekdayFare().subtract(discountPolicy.getQuantitativeDiscount());
                discountWeekendFare = accommodation.getWeekendFare().subtract(discountPolicy.getQuantitativeDiscount());
            } else {
                discountWeekdayFare = accommodation.getWeekdayFare()
                        .subtract(accommodation.getWeekdayFare().multiply(discountPolicy.getFixedRateDiscount()));
                discountWeekendFare = accommodation.getWeekendFare()
                        .subtract(accommodation.getWeekendFare().multiply(discountPolicy.getFixedRateDiscount()));
            }

            System.out.println("현재 평일 가격 : " + discountWeekdayFare);
            System.out.println("현재 주말 가격 : " + discountWeekendFare);
        } else {
            System.out.println("숙소에 할인이 적용 중이지 않습니다.");
            System.out.println("현재 평일 가격 : " + accommodation.getWeekdayFare());
            System.out.println("현재 주말 가격 : " + accommodation.getWeekendFare());
        }
        System.out.println("==============================");
    }
    @Override
    public void run() {
        sc.nextLine();
        calPrice();

        DiscountPolicy discountPolicy = discountPolicyControl.findByAccommodation(accommodation);
        if (discountPolicy == null) {
            discountPolicy = new DiscountPolicy();
        }
        System.out.println("==============================");
        System.out.println("할인 정책을 수정합니다.");
        System.out.println("==============================");
        System.out.print("할인 시작일 입력(yyyy-MM-dd) : "); LocalDateTime startDate = Keyboard.nextLocalDateTime();
        System.out.print("할인 종료일 입력(yyyy-MM-dd) : "); LocalDateTime endDate = Keyboard.nextLocalDateTime();
        if (endDate.isEqual(startDate) || endDate.isBefore(startDate)) {
            ErrorMessages.inverseEndDate();
            returnToParent();
            return;
        }

        System.out.print("할인 유형 입력(0 - 정량 할인, 1 - 정률 할인) : "); int isQuantitativeDiscount = sc.nextInt();
        System.out.print("할인량 입력 : "); BigDecimal discountFare = sc.nextBigDecimal();

        DateInfo dateInfo = new DateInfo(startDate, endDate);
        discountPolicy.setDateInfo(dateInfo);
        discountPolicy.setIsQuantitativeDiscount(isQuantitativeDiscount == 0);
        if (isQuantitativeDiscount == 0) {
            discountPolicy.setQuantitativeDiscount(discountFare);
        } else {
            discountPolicy.setFixedRateDiscount(discountFare);
        }
        discountPolicy.setAccommodation(accommodation);
        discountPolicy = discountPolicyControl.save(discountPolicy);
        System.out.println("할인 정책 수정 완료");

        accommodation.setDiscountPolicy(discountPolicy);
        accommodationControl.save(accommodation);
        calPrice();
        returnToParent();
    }
}
