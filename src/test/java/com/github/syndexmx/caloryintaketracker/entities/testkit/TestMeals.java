package com.github.syndexmx.caloryintaketracker.entities.testkit;

import com.github.syndexmx.caloryintaketracker.entities.Dish;
import com.github.syndexmx.caloryintaketracker.entities.Meal;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestMeals {

    public static Meal getTestMeal() {
        return Meal.builder()
                .id(0L)
                .date(LocalDate.parse("2000-01-01"))
                .dishList(new ArrayList<Dish>())
                .build();
    }

    public static Meal getTestMeal(int index) {
        return Meal.builder()
                .id((long)index)
                .date(LocalDate.parse("2025-05-06"))
                .dishList(new ArrayList<Dish>())
                .build();
    }

}
