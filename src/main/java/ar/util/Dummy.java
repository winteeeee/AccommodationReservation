package ar.util;

import ar.control.*;
import ar.entity.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Dummy {
    public static void dataInsert() {
        MemberControl memberControl = new MemberControl();
        AccommodationControl accommodationControl = new AccommodationControl();
        BasicAmenitiesControl basicAmenitiesControl = new BasicAmenitiesControl();
        TopAmenitiesGuestsSearchForControl topAmenitiesGuestsSearchForControl = new TopAmenitiesGuestsSearchForControl();
        SafetyAmenitiesControl safetyAmenitiesControl = new SafetyAmenitiesControl();
        AccessibilityAmenitiesControl accessibilityAmenitiesControl = new AccessibilityAmenitiesControl();
        ReservationControl reservationControl = new ReservationControl();
        ReviewControl reviewControl = new ReviewControl();

        List<Member> hostList = new ArrayList<>();
        Member dummyHost1 = new Member();
        dummyHost1.setId("host1");
        dummyHost1.setName("host1");
        dummyHost1.setPassword("host1");
        dummyHost1.setRoleType(RoleType.HOST);
        Member dummyHost2 = new Member();
        dummyHost2.setId("host2");
        dummyHost2.setName("host2");
        dummyHost2.setPassword("host2");
        dummyHost2.setRoleType(RoleType.HOST);
        Member dummyHost3 = new Member();
        dummyHost3.setId("host3");
        dummyHost3.setName("host3");
        dummyHost3.setPassword("host3");
        dummyHost3.setRoleType(RoleType.HOST);
        Member dummyHost4 = new Member();
        dummyHost4.setId("host4");
        dummyHost4.setName("host4");
        dummyHost4.setPassword("host4");
        dummyHost4.setRoleType(RoleType.HOST);
        hostList.add(dummyHost1);
        hostList.add(dummyHost2);
        hostList.add(dummyHost3);
        hostList.add(dummyHost4);
        memberControl.save(hostList);

        List<Member> guestList = new ArrayList<>();
        Member dummyGuest1 = new Member();
        dummyGuest1.setId("guest1");
        dummyGuest1.setName("guest1");
        dummyGuest1.setPassword("guest1");
        dummyGuest1.setRoleType(RoleType.GUEST);
        Member dummyGuest2 = new Member();
        dummyGuest2.setId("guest2");
        dummyGuest2.setName("guest2");
        dummyGuest2.setPassword("guest2");
        dummyGuest2.setRoleType(RoleType.GUEST);
        Member dummyGuest3 = new Member();
        dummyGuest3.setId("guest3");
        dummyGuest3.setName("guest3");
        dummyGuest3.setPassword("guest3");
        dummyGuest3.setRoleType(RoleType.GUEST);
        Member dummyGuest4 = new Member();
        dummyGuest4.setId("guest4");
        dummyGuest4.setName("guest4");
        dummyGuest4.setPassword("guest4");
        dummyGuest4.setRoleType(RoleType.GUEST);
        guestList.add(dummyGuest1);
        guestList.add(dummyGuest2);
        guestList.add(dummyGuest3);
        guestList.add(dummyGuest4);
        memberControl.save(guestList);

        List<Accommodation> accommodationList = new ArrayList<>();
        Accommodation dummyHouse1 = Accommodation.builder()
                .spaceType(SpaceType.PRIVATE_ROOM)
                .name("테스트 숙소1")
                .address("테스트 숙소1 주소")
                .host(dummyHost1)
                .accommodatedPerson(10)
                .room(5)
                .bedroom(2)
                .bed(2)
                .bathroom(1)
                .introduction("테스트 숙소1 소개")
                .weekdayFare(BigDecimal.valueOf(5000))
                .weekendFare(BigDecimal.valueOf(10000)).build();
        Accommodation dummyHouse2 = Accommodation.builder()
                .spaceType(SpaceType.ENTIRE_PLACE)
                .name("테스트 숙소2")
                .address("테스트 숙소2 주소")
                .host(dummyHost2)
                .accommodatedPerson(4)
                .room(1)
                .bedroom(1)
                .bed(1)
                .bathroom(1)
                .introduction("테스트 숙소2 소개")
                .weekdayFare(BigDecimal.valueOf(3000))
                .weekendFare(BigDecimal.valueOf(5000)).build();
        Accommodation dummyHouse3 = Accommodation.builder()
                .spaceType(SpaceType.ENTIRE_PLACE)
                .name("테스트 숙소3")
                .address("테스트 숙소3 주소")
                .host(dummyHost3)
                .accommodatedPerson(6)
                .room(2)
                .bedroom(1)
                .bed(1)
                .bathroom(1)
                .introduction("테스트 숙소3 소개")
                .weekdayFare(BigDecimal.valueOf(10000))
                .weekendFare(BigDecimal.valueOf(30000)).build();
        accommodationList.add(dummyHouse1);
        accommodationList.add(dummyHouse2);
        accommodationList.add(dummyHouse3);
        accommodationControl.save(accommodationList);

        List<BasicAmenities> basicAmenitiesList = new ArrayList<>();
        List<TopAmenitiesGuestsSearchFor> topAmenitiesGuestsSearchForList = new ArrayList<>();
        List<SafetyAmenities> safetyAmenitiesList = new ArrayList<>();
        List<AccessibilityAmenities> accessibilityAmenitiesList = new ArrayList<>();
        dummyHouse1 = accommodationControl.findByMember(dummyHost1).get(0);
        dummyHouse2 = accommodationControl.findByMember(dummyHost2).get(0);
        dummyHouse3 = accommodationControl.findByMember(dummyHost3).get(0);
        BasicAmenities dummyHouse1BA = BasicAmenities.builder()
                .toiletPaper(true)
                .soapForHandsAndBody(true)
                .oneTowelPerGuest(true)
                .linensForEachBed(true)
                .onePillowPerGuest(true)
                .cleaningSupplies(true).build();
        dummyHouse1BA.setAccommodation(dummyHouse1);
        TopAmenitiesGuestsSearchFor dummyHouse1Top = TopAmenitiesGuestsSearchFor.builder()
                .pool(true)
                .wifi(true)
                .kitchen(true)
                .freeParking(true)
                .jacuzzi(true)
                .washerOfDryer(true)
                .airConditioningOrHeating(true)
                .selfCheckIn(true)
                .laptopFriendlyWorkspace(true)
                .petsAllowed(true).build();
        dummyHouse1Top.setAccommodation(dummyHouse1);
        SafetyAmenities dummyHouse1SA = SafetyAmenities.builder()
                .carbonMonoxideAlarm(true)
                .smokeAlarm(true)
                .fireExtinguisher(true)
                .firstAidKit(true)
                .emergencyPlanAndLocalNumbers(true).build();
        dummyHouse1SA.setAccommodation(dummyHouse1);
        AccessibilityAmenities dummyHouse1AA = AccessibilityAmenities.builder()
                .stepFreeEntryway(true)
                .wideEntrances(true)
                .wideHallways(true)
                .accessibleBathroom(true).build();
        dummyHouse1AA.setAccommodation(dummyHouse1);
        BasicAmenities dummyHouse2BA = BasicAmenities.builder()
                .toiletPaper(true)
                .soapForHandsAndBody(true)
                .oneTowelPerGuest(true)
                .linensForEachBed(true)
                .onePillowPerGuest(true)
                .cleaningSupplies(true).build();
        dummyHouse2BA.setAccommodation(dummyHouse2);
        TopAmenitiesGuestsSearchFor dummyHouse2Top = TopAmenitiesGuestsSearchFor.builder()
                .pool(true)
                .wifi(true)
                .kitchen(true)
                .freeParking(true)
                .jacuzzi(true)
                .washerOfDryer(true)
                .airConditioningOrHeating(true)
                .selfCheckIn(true)
                .laptopFriendlyWorkspace(true)
                .petsAllowed(true).build();
        dummyHouse2Top.setAccommodation(dummyHouse2);
        SafetyAmenities dummyHouse2SA = SafetyAmenities.builder()
                .carbonMonoxideAlarm(true)
                .smokeAlarm(true)
                .fireExtinguisher(true)
                .firstAidKit(true)
                .emergencyPlanAndLocalNumbers(true).build();
        dummyHouse2SA.setAccommodation(dummyHouse2);
        AccessibilityAmenities dummyHouse2AA = AccessibilityAmenities.builder()
                .stepFreeEntryway(true)
                .wideEntrances(true)
                .wideHallways(true)
                .accessibleBathroom(true).build();
        dummyHouse2AA.setAccommodation(dummyHouse2);
        BasicAmenities dummyHouse3BA = BasicAmenities.builder()
                .toiletPaper(true)
                .soapForHandsAndBody(true)
                .oneTowelPerGuest(true)
                .linensForEachBed(true)
                .onePillowPerGuest(true)
                .cleaningSupplies(true).build();
        dummyHouse3BA.setAccommodation(dummyHouse3);
        TopAmenitiesGuestsSearchFor dummyHouse3Top = TopAmenitiesGuestsSearchFor.builder()
                .pool(true)
                .wifi(true)
                .kitchen(true)
                .freeParking(true)
                .jacuzzi(true)
                .washerOfDryer(true)
                .airConditioningOrHeating(true)
                .selfCheckIn(true)
                .laptopFriendlyWorkspace(true)
                .petsAllowed(true).build();
        dummyHouse3Top.setAccommodation(dummyHouse3);
        SafetyAmenities dummyHouse3SA = SafetyAmenities.builder()
                .carbonMonoxideAlarm(true)
                .smokeAlarm(true)
                .fireExtinguisher(true)
                .firstAidKit(true)
                .emergencyPlanAndLocalNumbers(true).build();
        dummyHouse3SA.setAccommodation(dummyHouse3);
        AccessibilityAmenities dummyHouse3AA = AccessibilityAmenities.builder()
                .stepFreeEntryway(true)
                .wideEntrances(true)
                .wideHallways(true)
                .accessibleBathroom(true).build();
        dummyHouse3AA.setAccommodation(dummyHouse3);
        basicAmenitiesList.add(dummyHouse1BA);
        topAmenitiesGuestsSearchForList.add(dummyHouse1Top);
        safetyAmenitiesList.add(dummyHouse1SA);
        accessibilityAmenitiesList.add(dummyHouse1AA);
        basicAmenitiesList.add(dummyHouse2BA);
        topAmenitiesGuestsSearchForList.add(dummyHouse2Top);
        safetyAmenitiesList.add(dummyHouse2SA);
        accessibilityAmenitiesList.add(dummyHouse2AA);
        basicAmenitiesList.add(dummyHouse3BA);
        topAmenitiesGuestsSearchForList.add(dummyHouse3Top);
        safetyAmenitiesList.add(dummyHouse3SA);
        accessibilityAmenitiesList.add(dummyHouse3AA);

        basicAmenitiesControl.save(basicAmenitiesList);
        topAmenitiesGuestsSearchForControl.save(topAmenitiesGuestsSearchForList);
        safetyAmenitiesControl.save(safetyAmenitiesList);
        accessibilityAmenitiesControl.save(accessibilityAmenitiesList);

        List<Reservation> reservations = new ArrayList<>();
        Reservation reservation1_1 = Reservation.builder()
                .guest(dummyGuest1)
                .accommodation(dummyHouse1)
                .review(null)
                .dateInfo(new DateInfo(LocalDateTime.of(2023, 11, 1, 0, 0), LocalDateTime.of(2023, 11, 2, 0, 0)))
                .person(10)
                .room(5)
                .fare(BigDecimal.valueOf(50000)).build();
        Reservation reservation1_2 = Reservation.builder()
                .guest(dummyGuest2)
                .accommodation(dummyHouse1)
                .review(null)
                .dateInfo(new DateInfo(LocalDateTime.of(2023, 11, 4, 0, 0), LocalDateTime.of(2023, 11, 5, 0, 0)))
                .person(2)
                .room(1)
                .fare(BigDecimal.valueOf(20000)).build();
        Reservation reservation1_3 = Reservation.builder()
                .guest(dummyGuest3)
                .accommodation(dummyHouse1)
                .review(null)
                .dateInfo(new DateInfo(LocalDateTime.of(2023, 11, 4, 0, 0), LocalDateTime.of(2023, 11, 5, 0, 0)))
                .person(4)
                .room(2)
                .fare(BigDecimal.valueOf(40000)).build();
        Reservation reservation2_1 = Reservation.builder()
                .guest(dummyGuest1)
                .accommodation(dummyHouse2)
                .review(null)
                .dateInfo(new DateInfo(LocalDateTime.of(2023, 11, 1, 0, 0), LocalDateTime.of(2023, 11, 2, 0, 0)))
                .person(2)
                .room(1)
                .fare(BigDecimal.valueOf(6000)).build();
        Reservation reservation2_2 = Reservation.builder()
                .guest(dummyGuest2)
                .accommodation(dummyHouse2)
                .review(null)
                .dateInfo(new DateInfo(LocalDateTime.of(2023, 11, 4, 0, 0), LocalDateTime.of(2023, 11, 5, 0, 0)))
                .person(1)
                .room(1)
                .fare(BigDecimal.valueOf(10000)).build();
        Reservation reservation2_3 = Reservation.builder()
                .guest(dummyGuest3)
                .accommodation(dummyHouse2)
                .review(null)
                .dateInfo(new DateInfo(LocalDateTime.of(2023, 11, 6, 0, 0), LocalDateTime.of(2023, 11, 10, 0, 0)))
                .person(4)
                .room(1)
                .fare(BigDecimal.valueOf(15000)).build();
        Reservation reservation3_1 = Reservation.builder()
                .guest(dummyGuest1)
                .accommodation(dummyHouse3)
                .review(null)
                .dateInfo(new DateInfo(LocalDateTime.of(2023, 11, 1, 0, 0), LocalDateTime.of(2023, 11, 2, 0, 0)))
                .person(6)
                .room(2)
                .fare(BigDecimal.valueOf(20000)).build();
        Reservation reservation3_2 = Reservation.builder()
                .guest(dummyGuest2)
                .accommodation(dummyHouse3)
                .review(null)
                .dateInfo(new DateInfo(LocalDateTime.of(2023, 11, 4, 0, 0), LocalDateTime.of(2023, 11, 5, 0, 0)))
                .person(2)
                .room(2)
                .fare(BigDecimal.valueOf(60000)).build();
        Reservation reservation3_3 = Reservation.builder()
                .guest(dummyGuest3)
                .accommodation(dummyHouse3)
                .review(null)
                .dateInfo(new DateInfo(LocalDateTime.of(2023, 11, 6, 0, 0), LocalDateTime.of(2023, 11, 10, 0, 0)))
                .person(4)
                .room(2)
                .fare(BigDecimal.valueOf(50000)).build();
        reservations.add(reservation1_1);
        reservations.add(reservation1_2);
        reservations.add(reservation1_3);
        reservations.add(reservation2_1);
        reservations.add(reservation2_2);
        reservations.add(reservation2_3);
        reservations.add(reservation3_1);
        reservations.add(reservation3_2);
        reservations.add(reservation3_3);
        reservations = reservationControl.save(reservations);

        List<Review> reviews = new ArrayList<>();
        Review review1_1 = Review.builder()
                .reservation(reservations.get(0))
                .star((byte) 5)
                .review("와! 너무 좋아요!").build();
        Review review1_2 = Review.builder()
                .reservation(reservations.get(1))
                .star((byte) 5)
                .review("깨끗하고 너무 좋았습니다 ^^ 다음에도 올게요.").build();
        Review review1_3 = Review.builder()
                .reservation(reservations.get(2))
                .star((byte) 5)
                .review("진짜 최고입니다;; 너무 만족했어요").build();
        Review review2_1 = Review.builder()
                .reservation(reservations.get(3))
                .star((byte) 3)
                .review("솔직히 마음에 들진 않지만.. 가격 생각하면 괜찮은 것 같습니다.").build();
        Review review2_2 = Review.builder()
                .reservation(reservations.get(4))
                .star((byte) 1)
                .review("절대 안올거같아요. 일단 너무 더럽네요.").build();
        Review review2_3 = Review.builder()
                .reservation(reservations.get(5))
                .star((byte) 4)
                .review("출장와서 편히 쉬다갑니다.").build();
        Review review3_1 = Review.builder()
                .reservation(reservations.get(6))
                .star((byte) 5)
                .review("친구 생일파티했는데 너무 쾌적했어요!").build();
        Review review3_2 = Review.builder()
                .reservation(reservations.get(7))
                .star((byte) 4)
                .review("시내랑도 가깝고 좋아요").build();
        Review review3_3 = Review.builder()
                .reservation(reservations.get(8))
                .star((byte) 3)
                .review("여자친구가 깨끗하다고 만족했어요! 저도 마음에 들었습니다.").build();
        reviews.add(review1_1);
        reviews.add(review1_2);
        reviews.add(review1_3);
        reviews.add(review2_1);
        reviews.add(review2_2);
        reviews.add(review2_3);
        reviews.add(review3_1);
        reviews.add(review3_2);
        reviews.add(review3_3);
        reviews = reviewControl.save(reviews);

        reservations.get(0).setReview(reviews.get(0));
        reservations.get(1).setReview(reviews.get(1));
        reservations.get(2).setReview(reviews.get(2));
        reservations.get(3).setReview(reviews.get(3));
        reservations.get(4).setReview(reviews.get(4));
        reservations.get(5).setReview(reviews.get(5));
        reservations.get(6).setReview(reviews.get(6));
        reservations.get(7).setReview(reviews.get(7));
        reservations.get(8).setReview(reviews.get(8));
        reservationControl.save(reservations);
    }
}
