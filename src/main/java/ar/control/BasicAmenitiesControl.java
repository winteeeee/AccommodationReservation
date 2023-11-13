package ar.control;

import ar.entity.Accommodation;
import ar.entity.BasicAmenities;
import ar.entity.QBasicAmenities;

public class BasicAmenitiesControl extends Control<BasicAmenities, Long> {
    private QBasicAmenities qBasicAmenities = QBasicAmenities.basicAmenities;
    public BasicAmenities findByAccommodation(Accommodation accommodation) {
        Transaction<BasicAmenities> fun = (em, query) -> query.selectFrom(qBasicAmenities)
                                                            .where(qBasicAmenities.accommodation.id.eq(accommodation.getId()))
                                                            .fetchOne();

        return transactionStart(fun);
    }
}
