package ar.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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
