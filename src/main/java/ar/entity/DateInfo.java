package ar.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class DateInfo {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
