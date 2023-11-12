package ar.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@DiscriminatorValue("Accessibility")
public class AccessibilityAmenities extends Amenities {
    private Long id;
    private boolean stepFreeEntryway;
    private boolean wideEntrances;
    private boolean wideHallways;
    private boolean accessibleBathroom;
}
