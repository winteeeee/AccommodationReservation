package ar.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccessibilityAmenities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean stepFreeEntryway;
    private boolean wideEntrances;
    private boolean wideHallways;
    private boolean accessibleBathroom;
    @OneToOne
    private Accommodation accommodation;
}
