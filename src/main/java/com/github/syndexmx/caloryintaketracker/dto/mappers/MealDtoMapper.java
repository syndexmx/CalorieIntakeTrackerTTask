package com.github.syndexmx.caloryintaketracker.dto.mappers;

import com.github.syndexmx.caloryintaketracker.dto.MealDto;
import com.github.syndexmx.caloryintaketracker.entities.Meal;

import java.time.LocalDate;

import static com.github.syndexmx.caloryintaketracker.dto.mappers.DishDtoMapper.*;

public class MealDtoMapper {

    public static Meal mealDtoToMeal(MealDto mealDto) {
        return Meal.builder()
                .id(mealDto.getId())
                .date(LocalDate.parse(mealDto.getDate()))
                .dishList(mealDto.getDishList().stream()
                        .map(dishDto -> dishDtoToDishIdOnly(dishDto)).toList())
                .build();
    }

    public static MealDto mealToMealDto(Meal meal) {
        return MealDto.builder()
                .id(meal.getId())
                .date(meal.getDate().toString())
                .dishList(meal.getDishList().stream()
                        .map(dish -> dishToDishDto(dish)).toList())
                .build();
    }

    public static Meal mealDtoToMealIdOnly(MealDto mealDto) {
        return Meal.builder()
                .id(mealDto.getId())
                .build();
    }
}
