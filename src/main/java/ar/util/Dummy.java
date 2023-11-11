package ar.util;

import ar.control.*;
import ar.entity.*;

import java.math.BigDecimal;
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
                .cleaningSupplies(true)
                .accommodation(dummyHouse1).build();
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
                .petsAllowed(true)
                .accommodation(dummyHouse1).build();
        SafetyAmenities dummyHouse1SA = SafetyAmenities.builder()
                .carbonMonoxideAlarm(true)
                .smokeAlarm(true)
                .fireExtinguisher(true)
                .firstAidKit(true)
                .emergencyPlanAndLocalNumbers(true)
                .accommodation(dummyHouse1).build();
        AccessibilityAmenities dummyHouse1AA = AccessibilityAmenities.builder()
                .stepFreeEntryway(true)
                .wideEntrances(true)
                .wideHallways(true)
                .accessibleBathroom(true)
                .accommodation(dummyHouse1).build();
        BasicAmenities dummyHouse2BA = BasicAmenities.builder()
                .toiletPaper(true)
                .soapForHandsAndBody(true)
                .oneTowelPerGuest(true)
                .linensForEachBed(true)
                .onePillowPerGuest(true)
                .cleaningSupplies(true)
                .accommodation(dummyHouse2).build();
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
                .petsAllowed(true)
                .accommodation(dummyHouse2).build();
        SafetyAmenities dummyHouse2SA = SafetyAmenities.builder()
                .carbonMonoxideAlarm(true)
                .smokeAlarm(true)
                .fireExtinguisher(true)
                .firstAidKit(true)
                .emergencyPlanAndLocalNumbers(true)
                .accommodation(dummyHouse2).build();
        AccessibilityAmenities dummyHouse2AA = AccessibilityAmenities.builder()
                .stepFreeEntryway(true)
                .wideEntrances(true)
                .wideHallways(true)
                .accessibleBathroom(true)
                .accommodation(dummyHouse2).build();
        BasicAmenities dummyHouse3BA = BasicAmenities.builder()
                .toiletPaper(true)
                .soapForHandsAndBody(true)
                .oneTowelPerGuest(true)
                .linensForEachBed(true)
                .onePillowPerGuest(true)
                .cleaningSupplies(true)
                .accommodation(dummyHouse3).build();
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
                .petsAllowed(true)
                .accommodation(dummyHouse3).build();
        SafetyAmenities dummyHouse3SA = SafetyAmenities.builder()
                .carbonMonoxideAlarm(true)
                .smokeAlarm(true)
                .fireExtinguisher(true)
                .firstAidKit(true)
                .emergencyPlanAndLocalNumbers(true)
                .accommodation(dummyHouse3).build();
        AccessibilityAmenities dummyHouse3AA = AccessibilityAmenities.builder()
                .stepFreeEntryway(true)
                .wideEntrances(true)
                .wideHallways(true)
                .accessibleBathroom(true)
                .accommodation(dummyHouse3).build();
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
    }
}
