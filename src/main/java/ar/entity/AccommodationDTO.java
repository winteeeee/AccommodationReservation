package ar.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccommodationDTO {
    private Long id;
    private String name;
    private SpaceType spaceType;
    private BigDecimal price;
    private Double averageStar;
}
