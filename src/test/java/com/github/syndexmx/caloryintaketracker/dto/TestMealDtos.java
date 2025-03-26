package com.github.syndexmx.caloryintaketracker.dto;

import java.util.ArrayList;

public class TestMealDtos {

    public static MealDto getTestMealDto() {
        return MealDto.builder()
                .id(0L)
                .date("2000-01-01")
                .dishList(new ArrayList<DishDto>())
                .build();
    }

    public static MealDto getTestMealDto(int index) {
        return MealDto.builder()
                .id((long)index)
                .date("2025-05-06")
                .dishList(new ArrayList<DishDto>())
                .build();
    }
}
