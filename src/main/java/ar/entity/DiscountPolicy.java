package ar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiscountPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    private DateInfo dateInfo;
    private Boolean isQuantitativeDiscount;
    private BigDecimal quantitativeDiscount;
    private BigDecimal fixedRateDiscount;
    @OneToOne
    private Accommodation accommodation;
}
