package ar.util;

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
}
