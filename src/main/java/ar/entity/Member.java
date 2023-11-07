package ar.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class Member {
    @Id
    private String id;
    private String password;
    private String name;
}
