package com.github.syndexmx.caloryintaketracker.services.impl;

import com.github.syndexmx.caloryintaketracker.entities.Meal;
import com.github.syndexmx.caloryintaketracker.repositories.MealRepository;
import com.github.syndexmx.caloryintaketracker.services.MealService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;

    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public Meal create(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public List<Meal> listAll() {
        final List<Meal> listOfMeals = mealRepository.findAll();
        return listOfMeals;
    }

    @Override
    public Optional<Meal> findById(Long id) {
        final Optional<Meal> mealFound = mealRepository.findById(id);
        return mealFound;
    }

    @Override
    public boolean isPresent(Long id) {
        return mealRepository.existsById(id);
    }

    @Override
    @Transactional
    public Optional<Meal> save(Meal meal) {
        boolean exists = mealRepository.existsById(meal.getId());
        if (!exists) {
            return Optional.empty();
        }
        final Meal dishSaved = mealRepository.save(meal);
        return Optional.ofNullable(dishSaved);
    }

    @Override
    public void deleteById(Long id) {
        mealRepository.deleteById(id);
    }
}
