package com.github.syndexmx.caloryintaketracker.controllers;

import com.github.syndexmx.caloryintaketracker.dto.DishDto;
import com.github.syndexmx.caloryintaketracker.entities.Dish;
import com.github.syndexmx.caloryintaketracker.services.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        dishDto.setId(null);
        Dish createdDish = dishService.create(dishDtoToDish(dishDto));
        DishDto returnDishDto = dishToDishDto(createdDish);
        return new ResponseEntity<>(returnDishDto, HttpStatus.CREATED);
    }

    @GetMapping("/api/v0/dishes")
    public ResponseEntity<List<DishDto>> listAllDishes() {
        List<Dish> dishList = dishService.listAll();
        List<DishDto> dishDtoList = dishList.stream()
                .map(dish -> dishToDishDto(dish))
                .toList();
        return new ResponseEntity<>(dishDtoList, HttpStatus.OK);
    }

    @GetMapping("/api/v0/dishes/{id}")
    public ResponseEntity<DishDto> findById(@PathVariable Long id) {
        Optional<Dish> optionalDish = dishService.findById(id);
        if (optionalDish.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        DishDto dishDto = dishToDishDto(optionalDish.get());
        return new ResponseEntity<>(dishDto, HttpStatus.FOUND);
    }

}
