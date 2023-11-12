package ar.util;

public class ErrorMessages {
    public static void invalidCommandError() {
        System.out.println("[오류] 잘못된 명령어입니다.");
    }
    public static void loginFailed() {
        System.out.println("[오류] 로그인에 실패하였습니다.");
    }

    public static void canNotFindHouse() {
        System.out.println("[오류] 숙소를 찾을 수 없습니다.");
    }

    public static void indexOutOfBound() {
        System.out.println("[오류] 잘못된 인덱스 범위입니다.");
    }

    public static void inverseEndDate() {
        //TODO 사용
        System.out.println("[오류] 끝 날짜는 시작 날짜보다 빠를 수 없습니다.");
    }

    public static void invalidDateFormat() {
        System.out.println("[오류] 날짜는 (yyyy-MM-dd) 꼴로 입력되어야 합니다.");
    }

    public static void completedReservationError() {
        System.out.println("[오류] 체크아웃이 완료된 예약은 취소할 수 없습니다.");
    }
}
