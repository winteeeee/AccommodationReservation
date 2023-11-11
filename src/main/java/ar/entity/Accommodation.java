package ar.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private SpaceType spaceType;
    private String name;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member host;
    private Integer accommodatedPerson;
    private Integer room;
    private Integer bedroom;
    private Integer bed;
    private Integer bathroom;
    private String introduction;
    private BigDecimal weekdayFare;
    private BigDecimal weekendFare;
    @OneToMany(mappedBy = "accommodation")
    private List<Reservation> reservations;
}
