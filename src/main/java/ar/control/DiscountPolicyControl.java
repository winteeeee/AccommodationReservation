package ar.control;

import ar.entity.Accommodation;
import ar.entity.DiscountPolicy;
import ar.entity.QDiscountPolicy;

public class DiscountPolicyControl extends Control<DiscountPolicy, Long> {
    private QDiscountPolicy qDiscountPolicy = QDiscountPolicy.discountPolicy;

    public DiscountPolicy findByAccommodation(Accommodation accommodation) {
        Transaction<DiscountPolicy> fun = (em, query) -> query.selectFrom(qDiscountPolicy)
                                                            .where(qDiscountPolicy.accommodation.id.eq(accommodation.getId()))
                                                            .fetchOne();

        return transactionStart(fun);
    }
}
