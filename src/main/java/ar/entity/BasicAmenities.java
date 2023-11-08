package ar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BasicAmenities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean toiletPaper;
    private boolean soapForHandsAndBody;
    private boolean oneTowelPerGuest;
    private boolean linensForEachBed;
    private boolean onePillowPerGuest;
    private boolean cleaningSupplies;
    @OneToOne
    private Accommodation accommodation;
}
