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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String date = sc.nextLine() + defaultTime;
        return LocalDateTime.parse(date, formatter);
    }
}
