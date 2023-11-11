package ar.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SafetyAmenities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean carbonMonoxideAlarm;
    private boolean smokeAlarm;
    private boolean fireExtinguisher;
    private boolean firstAidKit;
    private boolean emergencyPlanAndLocalNumbers;
    @OneToOne
    private Accommodation accommodation;
}
