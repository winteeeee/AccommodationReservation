package ar.control;

import ar.entity.*;

public class TopAmenitiesGuestsSearchForControl extends Control<TopAmenitiesGuestsSearchFor, Long> {
    private QTopAmenitiesGuestsSearchFor qTopAmenitiesGuestsSearchFor = QTopAmenitiesGuestsSearchFor.topAmenitiesGuestsSearchFor;
    public TopAmenitiesGuestsSearchFor findByAccommodation(Accommodation accommodation) {
        ReturnTransaction<TopAmenitiesGuestsSearchFor> fun = () -> query.selectFrom(qTopAmenitiesGuestsSearchFor)
                                                                            .where(qTopAmenitiesGuestsSearchFor.accommodation.id.eq(accommodation.getId()))
                                                                            .fetchOne();

        return transactionStart(fun);
    }
}
