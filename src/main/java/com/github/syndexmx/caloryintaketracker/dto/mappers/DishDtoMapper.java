package com.github.syndexmx.caloryintaketracker.dto.mappers;

import com.github.syndexmx.caloryintaketracker.dto.DishDto;
import com.github.syndexmx.caloryintaketracker.entities.Dish;

public class DishDtoMapper {

    public static Dish dishDtoToDish(DishDto dishDto) {
        return Dish.builder()
                .id(dishDto.getId())
                .name(dishDto.getName())
                .calories(dishDto.getCalories())
                .protein(dishDto.getProtein())
                .lipids(dishDto.getLipids())
                .carbohydrates(dishDto.getCarbohydrates())
                .build();
    }

    public static DishDto dishToDishDto(Dish dish) {
        return DishDto.builder()
                .id(dish.getId())
                .name(dish.getName())
                .calories(dish.getCalories())
                .protein(dish.getProtein())
                .lipids(dish.getLipids())
                .carbohydrates(dish.getCarbohydrates())
                .build();
    }

}
