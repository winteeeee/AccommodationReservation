package ar.control;

import ar.entity.*;

public class TopAmenitiesGuestsSearchForControl extends Control<TopAmenitiesGuestsSearchFor, Long> {
    private QTopAmenitiesGuestsSearchFor qTopAmenitiesGuestsSearchFor = QTopAmenitiesGuestsSearchFor.topAmenitiesGuestsSearchFor;
    public TopAmenitiesGuestsSearchFor findByAccommodation(Accommodation accommodation) {
        Transaction<TopAmenitiesGuestsSearchFor> fun = (em, query) -> query.selectFrom(qTopAmenitiesGuestsSearchFor)
                                                                            .where(qTopAmenitiesGuestsSearchFor.accommodation.id.eq(accommodation.getId()))
                                                                            .fetchOne();

        return transactionStart(fun);
    }
}
