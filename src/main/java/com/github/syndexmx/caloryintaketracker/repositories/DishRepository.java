package com.github.syndexmx.caloryintaketracker.repositories;

import com.github.syndexmx.caloryintaketracker.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
