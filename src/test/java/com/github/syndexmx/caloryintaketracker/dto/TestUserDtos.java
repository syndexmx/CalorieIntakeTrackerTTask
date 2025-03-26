package com.github.syndexmx.caloryintaketracker.dto;

import com.github.syndexmx.caloryintaketracker.entities.Meal;
import org.hibernate.dialect.function.array.ArrayAggFunction;

import java.util.ArrayList;

public class TestUserDtos {

    public static UserDto getTestUserDto() {
        return UserDto.builder()
                .id(0L)
                .name("John Connor")
                .email("jconnor@gmail.com")
                .sex("MALE")
                .birthDate("1985-02-28")
                .height(165)
                .weight(65000)
                .aim("KEEPING")
                .mealList(new ArrayList<MealDto>())
                .build();
    }

    public static UserDto getTestUserDto(int index) {
        if (index == 1) {
            return UserDto.builder()
                    .id(1L)
                    .name("Sarah Connor")
                    .email("connor_s@gmail.com")
                    .sex("FEMALE")
                    .birthDate("1965-11-13")
                    .height(160)
                    .weight(55000)
                    .aim("LOOSING")
                    .mealList(new ArrayList<MealDto>())
                    .build();
        }
        return UserDto.builder()
                .id(1000L)
                .name("Arnold")
                .email("schwartzneggerarny@gmail.com")
                .sex("MALE")
                .birthDate("1947-08-30")
                .height(180)
                .weight(90000)
                .aim("GAINING")
                .mealList(new ArrayList<MealDto>())
                .build();
    }
}
