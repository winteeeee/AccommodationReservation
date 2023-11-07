package ar.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @ManyToOne
    private Host host;
    private Integer accommodatedPerson;
    private Integer room;
    private Integer bedroom;
    private Integer bed;
    private Integer bathroom;
    private String introduction;
    private BigDecimal weekdayFare;
    private BigDecimal weekendFare;
}
