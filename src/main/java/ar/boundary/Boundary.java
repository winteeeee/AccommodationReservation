package ar.boundary;

import ar.AccommodationReservationApp;
import ar.util.Keyboard;

import java.util.Scanner;

public abstract class Boundary {
    protected AccommodationReservationApp app;
    protected Boundary parent;
    protected Scanner sc;

    public Boundary(AccommodationReservationApp app, Boundary parent) {
        this.app = app;
        this.parent = parent;
        sc = Keyboard.getInstance();
    }

    public abstract void run();

    public void returnToParent() {
        app.setBoundary(parent);
    }
}
