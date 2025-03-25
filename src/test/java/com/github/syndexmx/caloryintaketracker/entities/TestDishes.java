package com.github.syndexmx.caloryintaketracker.entities;

public class TestDishes {

    public static Dish getTestDish() {
        return Dish.builder()
                .id(1L)
                .name("Pizza")
                .calories(1000)
                .protein(5000)
                .lipids(20000)
                .carbohydrates(25000)
        .build();
    }

    public static Dish getTestDish(int index) {
        if (index == 1) {
            return Dish.builder()
                    .id(1L)
                    .name("Juice")
                    .calories(3000)
                    .protein(2000)
                    .lipids(1000)
                    .carbohydrates(30000)
                    .build();
        }
        return Dish.builder()
                .id(1000L)
                .name("Apple")
                .calories(100)
                .protein(500)
                .lipids(2000)
                .carbohydrates(33000)
                .build();
    }

}
