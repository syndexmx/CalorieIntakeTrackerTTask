package com.github.syndexmx.caloryintaketracker.services.impl;

import com.github.syndexmx.caloryintaketracker.entities.Dish;
import com.github.syndexmx.caloryintaketracker.repositories.DishRepository;
import com.github.syndexmx.caloryintaketracker.services.DishService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    @Transactional
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }
}
