package ar;

import ar.boundary.Boundary;
import ar.boundary.MainBoundary;
import ar.util.Dummy;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class AccommodationReservationApp {
    private Boundary boundary;

    public AccommodationReservationApp() {
        boundary = new MainBoundary();
    }

    public void run() {
        Dummy.dataInsert();

        while (true) {
            boundary.run(this);
        }
    }
}
