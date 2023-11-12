package ar;

import ar.boundary.Boundary;
import ar.boundary.MainBoundary;
import ar.entity.Member;
import ar.util.Dummy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccommodationReservationApp {
    private Member signedMember;
    private Boundary boundary;

    public AccommodationReservationApp() {
        boundary = new MainBoundary(this);
    }

    public void run() {
        Dummy.dataInsert();

        while (true) {
            boundary.run();
        }
    }
}
