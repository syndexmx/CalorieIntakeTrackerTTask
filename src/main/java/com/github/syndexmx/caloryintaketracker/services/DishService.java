package com.github.syndexmx.caloryintaketracker.services;

import com.github.syndexmx.caloryintaketracker.entities.Dish;
import org.springframework.stereotype.Service;

@Service
public interface DishService {
    Dish save(Dish dish);
}
