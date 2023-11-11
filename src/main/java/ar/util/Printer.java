package ar.util;

import java.time.LocalDate;
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
}
