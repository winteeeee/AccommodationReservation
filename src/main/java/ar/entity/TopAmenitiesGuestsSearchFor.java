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
@DiscriminatorValue("Top")
public class TopAmenitiesGuestsSearchFor extends Amenities {
    private Long id;
    private boolean pool;
    private boolean wifi;
    private boolean kitchen;
    private boolean freeParking;
    private boolean jacuzzi;
    private boolean washerOfDryer;
    private boolean airConditioningOrHeating;
    private boolean selfCheckIn;
    private boolean laptopFriendlyWorkspace;
    private boolean petsAllowed;
}
