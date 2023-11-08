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
