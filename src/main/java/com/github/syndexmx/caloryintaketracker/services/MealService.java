package com.github.syndexmx.caloryintaketracker.services;

import com.github.syndexmx.caloryintaketracker.entities.Meal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MealService {

    Meal create(Meal meal);
    List<Meal> listAll();
    Optional<Meal> findById(Long id);
    boolean isPresent(Long id);
    Optional<Meal> save(Meal meal);
    void deleteById(Long id);
}
