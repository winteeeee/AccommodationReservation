package ar.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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
