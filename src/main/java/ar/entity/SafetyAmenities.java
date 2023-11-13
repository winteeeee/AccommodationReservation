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
@DiscriminatorValue("Safety")
public class SafetyAmenities extends Amenities {
    private boolean carbonMonoxideAlarm;
    private boolean smokeAlarm;
    private boolean fireExtinguisher;
    private boolean firstAidKit;
    private boolean emergencyPlanAndLocalNumbers;
}
