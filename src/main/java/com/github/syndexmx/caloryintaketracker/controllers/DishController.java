package com.github.syndexmx.caloryintaketracker.controllers;

import com.github.syndexmx.caloryintaketracker.dto.DishDto;
import com.github.syndexmx.caloryintaketracker.entities.Dish;
import com.github.syndexmx.caloryintaketracker.services.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.github.syndexmx.caloryintaketracker.dto.mappers.DishMapper.dishDtoToDish;
import static com.github.syndexmx.caloryintaketracker.dto.mappers.DishMapper.dishToDishDto;

@RestController
@RequestMapping
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping("/api/v0/dishes")
    public ResponseEntity<DishDto> createDish(@RequestBody DishDto dishDto) {
        dishDto.setId(0L);
        Dish createdDish = dishService.save(dishDtoToDish(dishDto));
        DishDto returnDishDto = dishToDishDto(createdDish);
        return new ResponseEntity<>(returnDishDto, HttpStatus.CREATED);
    }

}
