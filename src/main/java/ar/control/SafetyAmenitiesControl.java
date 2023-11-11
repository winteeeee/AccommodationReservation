package ar.control;

import ar.entity.*;

public class SafetyAmenitiesControl extends Control<SafetyAmenities, Long> {
    public SafetyAmenities findByAccommodation(Accommodation accommodation) {
        ReturnTransaction<SafetyAmenities> fun = () -> {
            QSafetyAmenities qSafetyAmenities = QSafetyAmenities.safetyAmenities;
            return query.selectFrom(qSafetyAmenities).where(qSafetyAmenities.accommodation.id.eq(accommodation.getId())).fetchOne();
        };

        return transactionStart(fun);
    }
}
