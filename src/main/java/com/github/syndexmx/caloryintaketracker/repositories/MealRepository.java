package com.github.syndexmx.caloryintaketracker.repositories;

import com.github.syndexmx.caloryintaketracker.entities.Meal;
import com.github.syndexmx.caloryintaketracker.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "meal_entity-graph")
    List<Meal> findAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "meal_entity-graph")
    Optional<Meal> findById(Long id);
}
