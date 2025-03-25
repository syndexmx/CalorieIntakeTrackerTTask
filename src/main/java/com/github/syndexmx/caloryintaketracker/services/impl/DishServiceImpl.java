package com.github.syndexmx.caloryintaketracker.services.impl;

import com.github.syndexmx.caloryintaketracker.entities.Dish;
import com.github.syndexmx.caloryintaketracker.repositories.DishRepository;
import com.github.syndexmx.caloryintaketracker.services.DishService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public Dish create(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public List<Dish> listAll() {
        final List<Dish> listOfDishes = dishRepository.findAll();
        return listOfDishes;
    }

    @Override
    public Optional<Dish> findById(Long id) {
        final Optional<Dish> dishFound = dishRepository.findById(id);
        return dishFound;
    }

    @Override
    public boolean isPresent(Long id) {
        return dishRepository.existsById(id);
    }

    @Override
    @Transactional
    public Optional<Dish> save(Dish dish) {
        boolean exists = dishRepository.existsById(dish.getId());
        if (!exists) {
            return Optional.empty();
        }
        final Dish dishSaved = dishRepository.save(dish);
        return Optional.ofNullable(dishSaved);
    }

    @Override
    public void deleteById(Long id) {
        dishRepository.deleteById(id);
    }
}
