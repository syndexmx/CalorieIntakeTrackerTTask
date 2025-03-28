package com.github.syndexmx.caloryintaketracker.controllers;

import com.github.syndexmx.caloryintaketracker.dto.DishDto;
import com.github.syndexmx.caloryintaketracker.entities.Dish;
import com.github.syndexmx.caloryintaketracker.services.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.caloryintaketracker.dto.mappers.DishDtoMapper.dishDtoToDish;
import static com.github.syndexmx.caloryintaketracker.dto.mappers.DishDtoMapper.dishToDishDto;

@RestController
@RequestMapping
@Validated
@Tag(name = "Dish", description = "API блюда")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping("/api/v0/dishes")
    @Operation(summary = "Добавление блюда",
            description = "Позволяет добавить новое блюдо. id не важен, присваивается системой")
    public ResponseEntity<DishDto> createDish(@RequestBody @Validated DishDto dishDto) {
        dishDto.setId(null);
        Dish createdDish = dishService.create(dishDtoToDish(dishDto));
        DishDto returnDishDto = dishToDishDto(createdDish);
        return new ResponseEntity<>(returnDishDto, HttpStatus.CREATED);
    }

    @GetMapping("/api/v0/dishes")
    @Operation(summary = "Получение списка блюд",
            description = "Возвращает список всех блюд")
    public ResponseEntity<List<DishDto>> listAllDishes() {
        List<Dish> dishList = dishService.listAll();
        List<DishDto> dishDtoList = dishList.stream()
                .map(dish -> dishToDishDto(dish))
                .toList();
        return new ResponseEntity<>(dishDtoList, HttpStatus.OK);
    }

    @GetMapping("/api/v0/dishes/{id}")
    @Operation(summary = "Получение блюд по id",
            description = "Возвращает данные блюда")
    public ResponseEntity<DishDto> findById(@PathVariable Long id) {
        Optional<Dish> optionalDish = dishService.findById(id);
        if (optionalDish.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        DishDto dishDto = dishToDishDto(optionalDish.get());
        return new ResponseEntity<>(dishDto, HttpStatus.FOUND);
    }

    @PutMapping("/api/v0/dishes/{id}")
    @Operation(summary = "Коррекция блюда по id",
            description = "Позволяет обновить данные блюда, id в пути и в переданных данных должны совпадать")
    public ResponseEntity<DishDto> updateEntity(@PathVariable Long id,
            @RequestBody @Validated DishDto dishDto) {
        final Dish dish = dishDtoToDish(dishDto);
        if (!id.equals(dish.getId())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        if (!dishService.isPresent(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Optional<Dish> optionalSavedDish = dishService.save(dish);
        if (optionalSavedDish.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        DishDto savedDishDto = dishToDishDto(optionalSavedDish.get());
        ResponseEntity<DishDto> responseEntity = new ResponseEntity<DishDto>(
                savedDishDto, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @DeleteMapping("/api/v0/dishes/{id}")
    @Operation(summary = "Удалить блюдо по id",
            description = "Удаляет данные блюда из базы")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        dishService.deleteById(id);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
