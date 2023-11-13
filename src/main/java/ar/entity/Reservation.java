package ar.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Reservation extends Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member guest;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOMMODATION_ID")
    private Accommodation accommodation;
    @OneToOne
    private Review review;
    @Embedded
    private DateInfo dateInfo;
    private Integer person;
    private Integer room;
    private BigDecimal fare;
}
