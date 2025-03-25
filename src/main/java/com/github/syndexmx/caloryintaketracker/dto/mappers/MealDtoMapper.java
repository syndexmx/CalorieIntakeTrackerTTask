package com.github.syndexmx.caloryintaketracker.dto.mappers;

import com.github.syndexmx.caloryintaketracker.dto.MealDto;
import com.github.syndexmx.caloryintaketracker.entities.Meal;

import java.time.LocalDate;

public class MealDtoMapper {

    public static Meal mealDtoToMeal(MealDto mealDto) {
        return Meal.builder()
                .id(mealDto.getId())
                .date(LocalDate.parse(mealDto.getDate()))
                .build();
    }

    public static MealDto mealToMealDto(Meal meal) {
        return MealDto.builder()
                .id(meal.getId())
                .date(meal.getDate().toString())
                .build();
    }
}
