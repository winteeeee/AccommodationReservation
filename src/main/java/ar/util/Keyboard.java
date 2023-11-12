package ar.util;

import ar.entity.DateInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Keyboard {
    private static Scanner sc;

    private Keyboard() {}

    public static Scanner getInstance() {
        if (sc == null) {
            return sc = new Scanner(System.in);
        } else {
            return sc;
        }
    }

    public static LocalDateTime nextLocalDateTime() {
        String defaultTime = " 00:00:00";
        String date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        while (true) {
            date = sc.nextLine();

            if (date.length() != 10) {
                ErrorMessages.invalidDateFormat();
                System.out.print("재입력 : ");
            } else {
                break;
            }
        }

        return LocalDateTime.parse(date + defaultTime, formatter);
    }
}
