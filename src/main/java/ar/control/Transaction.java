package ar.control;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public interface Transaction<T> {
    T execute(EntityManager em, JPAQueryFactory query);
}
