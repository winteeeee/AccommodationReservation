package ar.control;

import ar.entity.*;

public class SafetyAmenitiesControl extends Control<SafetyAmenities, Long> {
    private QSafetyAmenities qSafetyAmenities = QSafetyAmenities.safetyAmenities;
    public SafetyAmenities findByAccommodation(Accommodation accommodation) {
        ReturnTransaction<SafetyAmenities> fun = () -> query.selectFrom(qSafetyAmenities)
                                                            .where(qSafetyAmenities.accommodation.id.eq(accommodation.getId()))
                                                            .fetchOne();

        return transactionStart(fun);
    }
}
