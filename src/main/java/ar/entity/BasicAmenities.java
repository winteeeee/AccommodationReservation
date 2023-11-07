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
    private Boolean toiletPaper;
    private Boolean soapForHandsAndBody;
    private Boolean oneTowelPerGuest;
    private Boolean linensForEachBed;
    private Boolean onePillowPerGuest;
    private Boolean cleaningSupplies;
    @OneToOne
    private Accommodation accommodation;
}
