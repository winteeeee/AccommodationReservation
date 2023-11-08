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
