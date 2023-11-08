package ar.boundary;

import ar.AccommodationReservationApp;
import ar.util.Keyboard;

import java.util.Scanner;

public abstract class Boundary {
    protected AccommodationReservationApp app;
    protected Scanner sc;

    public Boundary(AccommodationReservationApp app) {
        this.app = app;
        sc = Keyboard.getInstance();
    }

    public abstract void run();
}
