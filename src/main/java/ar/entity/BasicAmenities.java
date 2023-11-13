package ar.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@DiscriminatorValue("Basic")
public class BasicAmenities extends Amenities {
    private boolean toiletPaper;
    private boolean soapForHandsAndBody;
    private boolean oneTowelPerGuest;
    private boolean linensForEachBed;
    private boolean onePillowPerGuest;
    private boolean cleaningSupplies;
}
