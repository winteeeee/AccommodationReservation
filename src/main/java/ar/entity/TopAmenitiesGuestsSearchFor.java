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
    private Boolean pool;
    private Boolean wifi;
    private Boolean kitchen;
    private Boolean freeParking;
    private Boolean jacuzzi;
    private Boolean washerOfDryer;
    private Boolean airConditioningOrHeating;
    private Boolean selfCheckIn;
    private Boolean laptopFriendlyWorkspace;
    private Boolean petsAllowed;
    @OneToOne
    private Accommodation accommodation;
}
