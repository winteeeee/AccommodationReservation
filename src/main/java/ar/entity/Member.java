package ar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Entity {
    @Id
    private String id;
    private String password;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;
}
