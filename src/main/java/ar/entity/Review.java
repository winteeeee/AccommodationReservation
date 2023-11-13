package ar.entity;

import lombok.*;

import javax.persistence.*;

@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review extends Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Reservation reservation;
    private Byte star;
    private String review;
}
