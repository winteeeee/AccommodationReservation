package ar.boundary;

import ar.AccommodationReservationApp;
import ar.control.*;
import ar.entity.*;
import ar.util.Printer;

import java.time.LocalDate;
import java.util.List;

public class HouseDetailBoundary extends Boundary {
    private Accommodation accommodation;
    private ReservationControl reservationControl;
    private ReviewControl reviewControl;
    private BasicAmenitiesControl basicAmenitiesControl;
    private TopAmenitiesGuestsSearchForControl topAmenitiesGuestsSearchForControl;
    private SafetyAmenitiesControl safetyAmenitiesControl;
    private AccessibilityAmenitiesControl accessibilityAmenitiesControl;

    public HouseDetailBoundary(AccommodationReservationApp app, Boundary parent, Accommodation accommodation) {
        super(app, parent);
        this.accommodation = accommodation;
        reservationControl = new ReservationControl();
        reviewControl = new ReviewControl();
        basicAmenitiesControl = new BasicAmenitiesControl();
        topAmenitiesGuestsSearchForControl = new TopAmenitiesGuestsSearchForControl();
        safetyAmenitiesControl = new SafetyAmenitiesControl();
        accessibilityAmenitiesControl = new AccessibilityAmenitiesControl();
    }

    @Override
    public void run() {
        BasicAmenities basicAmenities = basicAmenitiesControl.findByAccommodation(accommodation);
        TopAmenitiesGuestsSearchFor topAmenitiesGuestsSearchFor = topAmenitiesGuestsSearchForControl.findByAccommodation(accommodation);
        SafetyAmenities safetyAmenities = safetyAmenitiesControl.findByAccommodation(accommodation);
        AccessibilityAmenities accessibilityAmenities = accessibilityAmenitiesControl.findByAccommodation(accommodation);

        System.out.println("==============================");
        System.out.println("선택한 숙소의 상세 정보는 아래와 같습니다.");
        System.out.println();
        System.out.println("숙소 이름 : " + accommodation.getName());
        System.out.println("숙소 주소 : " + accommodation.getAddress());
        System.out.println("숙소 소개 : " + accommodation.getIntroduction());
        System.out.println("숙소 공간 타입 : " + accommodation.getSpaceType().name());
        System.out.println("숙소 수용 인원 : " + accommodation.getAccommodatedPerson());
        System.out.println("숙소 방 개수 : " + accommodation.getRoom());
        System.out.println("숙소 침실 개수 : " + accommodation.getBedroom());
        System.out.println("숙소 침대 개수 : " + accommodation.getBed());
        System.out.println("숙소 욕실 개수 : " + accommodation.getBathroom());
        System.out.println("숙소 평일 가격 : " + accommodation.getWeekdayFare().toString());
        System.out.println("숙소 주말 가격 : " + accommodation.getWeekendFare().toString());

        System.out.println();
        System.out.println("[숙소 기본 편의시설]");
        System.out.println("화장지 : " + basicAmenities.isToiletPaper());
        System.out.println("손과 몸을 씻을 수 있는 비누 : " + basicAmenities.isSoapForHandsAndBody());
        System.out.println("게스트당 수건 1장 : " + basicAmenities.isOneTowelPerGuest());
        System.out.println("침대당 침구 1세트 : " + basicAmenities.isLinensForEachBed());
        System.out.println("게스트당 베개 1개 : " + basicAmenities.isOnePillowPerGuest());
        System.out.println("청소용품 : " + basicAmenities.isCleaningSupplies());

        System.out.println();
        System.out.println("[게스트가 가장 많이 검색하는 편의시설]");
        System.out.println("수영장 : " + topAmenitiesGuestsSearchFor.isPool());
        System.out.println("와이파이 : " + topAmenitiesGuestsSearchFor.isWifi());
        System.out.println("주방 : " + topAmenitiesGuestsSearchFor.isKitchen());
        System.out.println("무료 주차 공간 : " + topAmenitiesGuestsSearchFor.isFreeParking());
        System.out.println("자쿠지 : " + topAmenitiesGuestsSearchFor.isJacuzzi());
        System.out.println("세탁기 또는 건조기 : " + topAmenitiesGuestsSearchFor.isWasherOfDryer());
        System.out.println("에어컨 또는 난방 : " + topAmenitiesGuestsSearchFor.isAirConditioningOrHeating());
        System.out.println("셀프 체크인 : " + topAmenitiesGuestsSearchFor.isSelfCheckIn());
        System.out.println("노트북 작업 공간 : " + topAmenitiesGuestsSearchFor.isLaptopFriendlyWorkspace());
        System.out.println("반려동물 동반 가능 : " + topAmenitiesGuestsSearchFor.isPetsAllowed());

        System.out.println();
        System.out.println("[안전 편의시설]");
        System.out.println("일산화탄소 경보기 : " + safetyAmenities.isCarbonMonoxideAlarm());
        System.out.println("화재 경보기 : " + safetyAmenities.isSmokeAlarm());
        System.out.println("소화기 : " + safetyAmenities.isFireExtinguisher());
        System.out.println("구급상자 : " + safetyAmenities.isFirstAidKit());
        System.out.println("비상 대피 안내도 및 현지 응급 구조기관 번호 : " + safetyAmenities.isEmergencyPlanAndLocalNumbers());

        System.out.println();
        System.out.println("[접근성 편의시설]");
        System.out.println("계단이나 단차가 없는 현관 : " + accessibilityAmenities.isStepFreeEntryway());
        System.out.println("폭 32인치/81cm 이상의 넓은 출입구 : " + accessibilityAmenities.isWideEntrances());
        System.out.println("폭 36인치/91cm 이상의 넓은 복도 : " + accessibilityAmenities.isWideHallways());
        System.out.println("휠체어 접근 가능 욕실 : " + accessibilityAmenities.isAccessibleBathroom());
        System.out.println("==============================");

        System.out.println("==============================");
        System.out.println("선택한 숙소의 모든 리뷰는 아래와 같습니다.");
        List<Review> list = reviewControl.findByReservations(accommodation.getReservations());
        if (list != null) {
            list.forEach((e) -> {
                Printer.showStar(e.getStar());
                System.out.println();
                System.out.println(e.getReview());
                System.out.println();
                System.out.println();
            });
        }
        System.out.println("==============================");

        System.out.println("==============================");
        System.out.println("예약 현황을 확인합니다.");
        int month = LocalDate.now().getMonth().getValue();
        while (1 <= month && month <= 12) {
            List<Integer> roomCount = reservationControl.findSumOfRoomByAccommodationAndYearAndMonth(accommodation, LocalDate.now().getYear(), month);
            Printer.showReservationStatus(accommodation, month, roomCount);
            System.out.print("추가 예약 현황을 볼 달 입력(1 ~ 12 이외의 값 입력 시 종료) : ");
            month = sc.nextInt();
        }
        System.out.println("==============================");

        returnToParent();
    }
}
