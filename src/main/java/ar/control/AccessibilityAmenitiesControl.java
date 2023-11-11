package ar.control;

import ar.entity.*;

public class AccessibilityAmenitiesControl extends Control<AccessibilityAmenities, Long> {
    public AccessibilityAmenities findByAccommodation(Accommodation accommodation) {
        ReturnTransaction<AccessibilityAmenities> fun = () -> {
            QAccessibilityAmenities qAccessibilityAmenities = QAccessibilityAmenities.accessibilityAmenities;
            return query.selectFrom(qAccessibilityAmenities).where(qAccessibilityAmenities.accommodation.id.eq(accommodation.getId())).fetchOne();
        };

        return transactionStart(fun);
    }
}
