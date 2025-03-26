package com.github.syndexmx.caloryintaketracker.dto.mappers;

import com.github.syndexmx.caloryintaketracker.dto.MealDto;
import com.github.syndexmx.caloryintaketracker.dto.testkit.TestMealDtos;
import com.github.syndexmx.caloryintaketracker.entities.Meal;
import com.github.syndexmx.caloryintaketracker.entities.testkit.TestMeals;
import org.junit.jupiter.api.Test;

import static com.github.syndexmx.caloryintaketracker.dto.mappers.MealDtoMapper.mealDtoToMeal;
import static com.github.syndexmx.caloryintaketracker.dto.mappers.MealDtoMapper.mealToMealDto;
import static org.junit.jupiter.api.Assertions.*;

class MealDtoMapperTest {

    @Test
    void testMealDtoToMeal() {
        MealDto mealDto = TestMealDtos.getTestMealDto();
        Meal expected = TestMeals.getTestMeal();
        Meal actual = mealDtoToMeal(mealDto);
        assertEquals(expected, actual);
    }

    @Test
    void testMealToMealDto() {
        Meal meal = TestMeals.getTestMeal();
        MealDto expected = TestMealDtos.getTestMealDto();
        MealDto actual = mealToMealDto(meal);
        assertEquals(expected, actual);
    }
}