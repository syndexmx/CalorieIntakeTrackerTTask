package com.github.syndexmx.caloryintaketracker.dto.testkit;

import com.github.syndexmx.caloryintaketracker.dto.DishDto;

public class TestDishDtos {

    public static DishDto getTestDishDto() {
        return DishDto.builder()
                .id(0L)
                .name("Pizza")
                .calories(1000)
                .protein(5000)
                .lipids(20000)
                .carbohydrates(25000)
                .build();
    }

    public static DishDto getTestDishDto(int index) {
        if (index == 1) {
            return DishDto.builder()
                    .id(1L)
                    .name("Juice")
                    .calories(3000)
                    .protein(2000)
                    .lipids(1000)
                    .carbohydrates(30000)
                    .build();
        }
        return DishDto.builder()
                .id(1000L)
                .name("Apple")
                .calories(100)
                .protein(500)
                .lipids(2000)
                .carbohydrates(33000)
                .build();
    }
}
