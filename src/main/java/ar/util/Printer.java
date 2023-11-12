package ar.util;

import ar.entity.Accommodation;
import ar.entity.SpaceType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Printer {
    public static void showStar(Byte star) {
        for (int i = 0; i < star; i++) {
            System.out.print("★");
        }
    }

    public static void showCalendar(int month, List<String> dayElement) {
        Calendar cal = Calendar.getInstance();
        cal.set(LocalDate.now().getYear(), month - 1, 1);
        int limitDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int curDay = cal.get(Calendar.DAY_OF_WEEK);

        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃  일  ┃  월  ┃  화  ┃  수  ┃  목  ┃  금  ┃  토  ┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        for (int i = 0; i < curDay - 1; i++) {
            System.out.print("┃      ");
        }
        System.out.print("┃  ");
        boolean isFirst = true;
        for (int idx = 0; idx < limitDay; curDay++, idx++) {
            if (isFirst) {
                System.out.print(dayElement.get(idx) + "  ");
                isFirst = false;
            } else {
                System.out.print("┃  ");
                System.out.print(dayElement.get(idx) + "  ");
            }

            if (curDay % 7 == 0) {
                System.out.println("┃");
                System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            }
        }

        curDay %= 7;
        for (; curDay <= 7; curDay++) {
            System.out.print("┃      ");
        }
        System.out.println("┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
    }

    public static void showReservationStatus(Accommodation accommodation, int month, List<Integer> roomCount) {
        List<String> dayElement = new ArrayList<>();
        roomCount.forEach((e) -> {
            if (accommodation.getSpaceType() == SpaceType.ENTIRE_PLACE) {
                if (e != null) {
                    dayElement.add("無");
                } else {
                    dayElement.add("有");
                }
            } else if (accommodation.getSpaceType() == SpaceType.PRIVATE_ROOM) {
                if (e != null) {
                    e = e > accommodation.getRoom() ? 0 : accommodation.getRoom() - e;
                } else {
                    e = accommodation.getRoom();
                }

                if (e < 10) {
                    dayElement.add("0" + e);
                } else {
                    dayElement.add(String.valueOf(e));
                }
            }
        });

        showCalendar(month, dayElement);
    }
}
