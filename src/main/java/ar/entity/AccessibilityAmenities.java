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
@DiscriminatorValue("Accessibility")
public class AccessibilityAmenities extends Amenities {
    private boolean stepFreeEntryway;
    private boolean wideEntrances;
    private boolean wideHallways;
    private boolean accessibleBathroom;
}
