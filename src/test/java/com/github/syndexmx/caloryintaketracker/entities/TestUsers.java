package com.github.syndexmx.caloryintaketracker.entities;

import com.github.syndexmx.caloryintaketracker.dto.MealDto;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestUsers {

    public static User getTestUser() {
        return User.builder()
                .id(0L)
                .name("John Connor")
                .email("jconnor@gmail.com")
                .sex(Sexes.MALE)
                .birthDate(LocalDate.parse("1985-02-28"))
                .height(165)
                .weight(65000)
                .aim(Aims.KEEPING)
                .mealList(new ArrayList<Meal>())
                .build();
    }

    public static User getTestUser(int index) {
        if (index == 1) {
            return User.builder()
                    .id(1L)
                    .name("Sarah Connor")
                    .email("connor_s@gmail.com")
                    .sex(Sexes.FEMALE)
                    .birthDate(LocalDate.parse("1965-11-13"))
                    .height(160)
                    .weight(55000)
                    .aim(Aims.LOOSING)
                    .mealList(new ArrayList<Meal>())
                    .build();
        }
        return User.builder()
                .id(1000L)
                .name("Arnold")
                .email("schwartzneggerarny@gmail.com")
                .sex(Sexes.MALE)
                .birthDate(LocalDate.parse("1947-08-30"))
                .height(180)
                .weight(90000)
                .aim(Aims.GAINING)
                .mealList(new ArrayList<Meal>())
                .build();
    }

}

