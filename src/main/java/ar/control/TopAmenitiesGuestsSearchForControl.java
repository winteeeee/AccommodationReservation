package ar.control;

import ar.entity.*;

public class TopAmenitiesGuestsSearchForControl extends Control<TopAmenitiesGuestsSearchFor, Long> {
    public TopAmenitiesGuestsSearchFor findByAccommodation(Accommodation accommodation) {
        ReturnTransaction<TopAmenitiesGuestsSearchFor> fun = () -> {
            QTopAmenitiesGuestsSearchFor qTopAmenitiesGuestsSearchFor = QTopAmenitiesGuestsSearchFor.topAmenitiesGuestsSearchFor;
            return query.selectFrom(qTopAmenitiesGuestsSearchFor).where(qTopAmenitiesGuestsSearchFor.accommodation.id.eq(accommodation.getId())).fetchOne();
        };

        return transactionStart(fun);
    }
}
