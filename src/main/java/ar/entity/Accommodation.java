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
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    private Host host;
    private Integer accommodatedPerson;
    private Integer room;
    private String introduction;
    private Integer bedroom;
    private Integer bed;
    private Integer bathroom;
    private BigDecimal weekdayFare;
    private BigDecimal weekendFare;
}
