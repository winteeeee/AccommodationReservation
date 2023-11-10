package ar.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccommodationDTO {
    private String name;
    private SpaceType spaceType;
    private BigDecimal weekdayFare;
    private BigDecimal weekendFare;
    private Double averageStar;
}
