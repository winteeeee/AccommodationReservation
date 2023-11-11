package ar.control;

import ar.entity.Accommodation;
import ar.entity.BasicAmenities;
import ar.entity.QAccommodation;
import ar.entity.QBasicAmenities;

public class BasicAmenitiesControl extends Control<BasicAmenities, Long> {
    public BasicAmenities findByAccommodation(Accommodation accommodation) {
        ReturnTransaction<BasicAmenities> fun = () -> {
            QBasicAmenities qBasicAmenities = QBasicAmenities.basicAmenities;
            return query.selectFrom(qBasicAmenities).where(qBasicAmenities.accommodation.id.eq(accommodation.getId())).fetchOne();
        };

        return transactionStart(fun);
    }
}
