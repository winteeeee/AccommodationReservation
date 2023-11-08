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
public class TopAmenitiesGuestsSearchFor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @OneToOne
    private Accommodation accommodation;
}
