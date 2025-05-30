package com.github.syndexmx.caloryintaketracker.services;

import com.github.syndexmx.caloryintaketracker.entities.Dish;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DishService {

    Dish create(Dish dish);
    List<Dish> listAll();
    Optional<Dish> findById(Long id);
    boolean isPresent(Long id);
    Optional<Dish> save(Dish dish);
    void deleteById(Long id);

}
