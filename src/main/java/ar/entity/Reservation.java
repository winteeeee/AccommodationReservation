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
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Guest guest;
    @ManyToOne
    private Accommodation accommodation;
    @Embedded
    private DateInfo dateInfo;
    private Integer person;
    private Integer room;
    private BigDecimal fare;
}
