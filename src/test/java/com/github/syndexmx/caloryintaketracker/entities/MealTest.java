package com.github.syndexmx.caloryintaketracker.entities;

import com.github.syndexmx.caloryintaketracker.entities.testkit.TestDishes;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void getCalories() {
        Dish dish = TestDishes.getTestDish();
        Dish dish2 = TestDishes.getTestDish(2);
        Meal meal = Meal.builder()
                .id(0L)
                .date(LocalDate.parse("2000-01-01"))
                .dishList(List.of(dish, dish2))
                .build();
        int calculatedCalories = meal.getCalories();
        int expected = 1100;
        assertEquals(expected ,calculatedCalories);
    }
}