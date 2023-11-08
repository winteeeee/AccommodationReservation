package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.AccessibilityAmenitiesControl;
import ar.control.BasicAmenitiesControl;
import ar.control.SafetyAmenitiesControl;
import ar.control.TopAmenitiesGuestsSearchForControl;
import ar.entity.*;
import ar.util.Keyboard;

import java.util.Scanner;

public class AmenitiesBoundary extends Boundary implements Returnable {
    private Boundary parent;
    private Accommodation accommodation;
    private Scanner sc = Keyboard.getInstance();
    private BasicAmenitiesControl basicAmenitiesControl;
    private TopAmenitiesGuestsSearchForControl topAmenitiesGuestsSearchForControl;
    private SafetyAmenitiesControl safetyAmenitiesControl;
    private AccessibilityAmenitiesControl accessibilityAmenitiesControl;

    public AmenitiesBoundary(AccommodationReservationApp app, Boundary parent, Accommodation accommodation) {
        super(app);
        this.parent = parent;
        this.accommodation = accommodation;
        basicAmenitiesControl = new BasicAmenitiesControl();
        topAmenitiesGuestsSearchForControl = new TopAmenitiesGuestsSearchForControl();
        safetyAmenitiesControl = new SafetyAmenitiesControl();
        accessibilityAmenitiesControl = new AccessibilityAmenitiesControl();
    }

    private void registBasicAmenities() {
        BasicAmenities basicAmenities = new BasicAmenities();
        boolean[] selected = new boolean[8];
        final int TOILET_PAPER = 1;
        final int SOAP_FOR_HANDS_AND_BODY = 2;
        final int ONE_TOWEL_PER_GUEST = 3;
        final int LINENS_FOR_EACH_BED = 4;
        final int ONE_PILLOW_PER_GUEST = 5;
        final int CLEANING_SUPPLIES = 6;
        final int QUIT = 7;
        int command = 0;

        System.out.println("기본 편의시설을 선택해주세요.");
        System.out.println("1. 화장지");
        System.out.println("2. 손과 몸을 씻을 수 있는 비누");
        System.out.println("3. 게스트당 수건 1장");
        System.out.println("4. 침대당 침구 1세트");
        System.out.println("5. 게스트당 베개 1개");
        System.out.println("6. 청소 용품");
        System.out.println("7. 입력 종료");

        System.out.print("입력 : ");
        while (command != QUIT) {
            command = sc.nextInt();
            selected[command] = true;
        }

        if (selected[TOILET_PAPER])
            basicAmenities.setToiletPaper(true);
        if (selected[SOAP_FOR_HANDS_AND_BODY])
            basicAmenities.setSoapForHandsAndBody(true);
        if (selected[ONE_TOWEL_PER_GUEST])
            basicAmenities.setOneTowelPerGuest(true);
        if (selected[LINENS_FOR_EACH_BED])
            basicAmenities.setLinensForEachBed(true);
        if (selected[ONE_PILLOW_PER_GUEST])
            basicAmenities.setOnePillowPerGuest(true);
        if (selected[CLEANING_SUPPLIES])
            basicAmenities.setCleaningSupplies(true);
        basicAmenities.setAccommodation(accommodation);
        basicAmenitiesControl.save(basicAmenities);
    }

    private void registTopAmenities() {
        TopAmenitiesGuestsSearchFor topAmenitiesGuestsSearchFor = new TopAmenitiesGuestsSearchFor();
        boolean[] selected = new boolean[12];
        final int POOL = 1;
        final int WIFI = 2;
        final int KITCHEN = 3;
        final int FREE_PARKING = 4;
        final int JACUZZI = 5;
        final int WASHER_OF_DRYER = 6;
        final int AIR_CONDITIONING_OR_HEATING = 7;
        final int SELF_CHECK_IN = 8;
        final int LAPTOP_FRIENDLY_WORKSPACE = 9;
        final int PETS_ALLOWED = 10;
        final int QUIT = 11;
        int command = 0;

        System.out.println("게스트가 가장 많이; 검색하는 편의시설을 선택해주세요.");
        System.out.println("1. 수영장");
        System.out.println("2. 와이파이");
        System.out.println("3. 주방");
        System.out.println("4. 무료 주차 공간");
        System.out.println("5. 자쿠지");
        System.out.println("6. 세탁기 또는 건조기");
        System.out.println("7. 에어컨 또는 난방");
        System.out.println("8. 셀프 체크인");
        System.out.println("9. 노트북 작업 공간");
        System.out.println("10. 반려동물 동반 가능");
        System.out.println("11. 입력 종료");

        System.out.print("입력 : ");
        while (command != QUIT) {
            command = sc.nextInt();
            selected[command] = true;
        }

        if (selected[POOL])
            topAmenitiesGuestsSearchFor.setPool(true);
        if (selected[WIFI])
            topAmenitiesGuestsSearchFor.setWifi(true);
        if (selected[KITCHEN])
            topAmenitiesGuestsSearchFor.setKitchen(true);
        if (selected[FREE_PARKING])
            topAmenitiesGuestsSearchFor.setFreeParking(true);
        if (selected[JACUZZI])
            topAmenitiesGuestsSearchFor.setJacuzzi(true);
        if (selected[WASHER_OF_DRYER])
            topAmenitiesGuestsSearchFor.setWasherOfDryer(true);
        if (selected[AIR_CONDITIONING_OR_HEATING])
            topAmenitiesGuestsSearchFor.setAirConditioningOrHeating(true);
        if (selected[SELF_CHECK_IN])
            topAmenitiesGuestsSearchFor.setSelfCheckIn(true);
        if (selected[LAPTOP_FRIENDLY_WORKSPACE])
            topAmenitiesGuestsSearchFor.setLaptopFriendlyWorkspace(true);
        if (selected[PETS_ALLOWED])
            topAmenitiesGuestsSearchFor.setPetsAllowed(true);
        topAmenitiesGuestsSearchFor.setAccommodation(accommodation);
        topAmenitiesGuestsSearchForControl.save(topAmenitiesGuestsSearchFor);
    }

    private void registSafetyAmenities() {
        SafetyAmenities safetyAmenities = new SafetyAmenities();
        boolean[] selected = new boolean[7];
        final int CARBON_MONOXIDE_ALARM = 1;
        final int SMOKE_ALARM = 2;
        final int FIRE_EXTINGUISHER = 3;
        final int FIRST_AID_KIT = 4;
        final int EMERGENCY_PLAN_AND_LOCAL_NUMBERS = 5;
        final int QUIT = 6;
        int command = 0;

        System.out.println("게스트가 가장 많이; 검색하는 편의시설을 선택해주세요.");
        System.out.println("1. 일산화탄소 경보기");
        System.out.println("2. 화재 경보기");
        System.out.println("3. 소화기");
        System.out.println("4. 구급상자");
        System.out.println("5. 비상 대피 안내도 및 현지 응급 구조기관 번호");
        System.out.println("6. 입력 종료");

        System.out.print("입력 : ");
        while (command != QUIT) {
            command = sc.nextInt();
            selected[command] = true;
        }

        if (selected[CARBON_MONOXIDE_ALARM])
            safetyAmenities.setCarbonMonoxideAlarm(true);
        if (selected[SMOKE_ALARM])
            safetyAmenities.setSmokeAlarm(true);
        if (selected[FIRE_EXTINGUISHER])
            safetyAmenities.setFireExtinguisher(true);
        if (selected[FIRST_AID_KIT])
            safetyAmenities.setFirstAidKit(true);
        if (selected[EMERGENCY_PLAN_AND_LOCAL_NUMBERS])
            safetyAmenities.setEmergencyPlanAndLocalNumbers(true);
        safetyAmenities.setAccommodation(accommodation);
        safetyAmenitiesControl.save(safetyAmenities);
    }

    private void registAccessibilityAmenities() {
        AccessibilityAmenities accessibilityAmenities = new AccessibilityAmenities();
        boolean[] selected = new boolean[6];
        final int STEP_FREE_ENTRYWAY = 1;
        final int WIDE_ENTRANCES = 2;
        final int WIDE_HALLWAYS = 3;
        final int ACCESSIBLE_BATHROOM = 4;
        final int QUIT = 5;
        int command = 0;

        System.out.println("게스트가 가장 많이; 검색하는 편의시설을 선택해주세요.");
        System.out.println("1. 계단이나 단차가 없는 현관");
        System.out.println("2. 폭 32인치/81cm 이상의 넓은 출입구");
        System.out.println("3. 폭 36인치/91cm 이상의 넓은 복도");
        System.out.println("4. 휠체어 접근 가능 욕실");
        System.out.println("5. 입력 종료");

        System.out.print("입력 : ");
        while (command != QUIT) {
            command = sc.nextInt();
            selected[command] = true;
        }

        if (selected[STEP_FREE_ENTRYWAY])
            accessibilityAmenities.setStepFreeEntryway(true);
        if (selected[WIDE_ENTRANCES])
            accessibilityAmenities.setWideEntrances(true);
        if (selected[WIDE_HALLWAYS])
            accessibilityAmenities.setWideHallways(true);
        if (selected[ACCESSIBLE_BATHROOM])
            accessibilityAmenities.setAccessibleBathroom(true);
        accessibilityAmenities.setAccommodation(accommodation);
        accessibilityAmenitiesControl.save(accessibilityAmenities);
    }
    @Override
    public void run() {
        registBasicAmenities();
        registTopAmenities();
        registSafetyAmenities();
        registAccessibilityAmenities();
        System.out.println("숙소가 성공적으로 등록되었습니다!");
        returnToParent();
    }

    @Override
    public void returnToParent() {
        app.setBoundary(parent);
    }
}
