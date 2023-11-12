package ar.control;

import ar.entity.*;

public class AccessibilityAmenitiesControl extends Control<AccessibilityAmenities, Long> {
    private QAccessibilityAmenities qAccessibilityAmenities = QAccessibilityAmenities.accessibilityAmenities;
    public AccessibilityAmenities findByAccommodation(Accommodation accommodation) {
        ReturnTransaction<AccessibilityAmenities> fun = () -> query.selectFrom(qAccessibilityAmenities)
                                                                    .where(qAccessibilityAmenities.accommodation.id.eq(accommodation.getId()))
                                                                    .fetchOne();

        return transactionStart(fun);
    }
}
