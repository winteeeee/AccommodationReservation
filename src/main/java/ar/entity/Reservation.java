package ar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
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
