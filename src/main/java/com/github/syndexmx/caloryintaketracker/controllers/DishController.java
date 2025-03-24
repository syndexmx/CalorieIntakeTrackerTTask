package com.github.syndexmx.caloryintaketracker.controllers;

import com.github.syndexmx.caloryintaketracker.dto.DishDto;
import com.github.syndexmx.caloryintaketracker.entities.Dish;
import com.github.syndexmx.caloryintaketracker.services.DishService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.syndexmx.caloryintaketracker.dto.mappers.DishMapper.dishDtoToDish;

@RestController
@RequestMapping("v0/dish")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public Dish createDish(@RequestBody DishDto dishDto) {
        Dish createdDish = dishService.save(dishDtoToDish(dishDto));
        return null;
    }

}
