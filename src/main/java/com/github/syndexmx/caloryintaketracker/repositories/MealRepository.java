package com.github.syndexmx.caloryintaketracker.repositories;

import com.github.syndexmx.caloryintaketracker.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
}
