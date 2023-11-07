package ar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SafetyAmenities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean carbonMonoxideAlarm;
    private Boolean smokeAlarm;
    private Boolean fireExtinguisher;
    private Boolean firstAidKit;
    private Boolean emergencyPlanAndLocalNumbers;
}
