package com.github.syndexmx.caloryintaketracker.dto;

import java.util.ArrayList;

public class TestUserShallowDtos {

    public static UserShallowDto getTestUserShallowDto() {
        return UserShallowDto.builder()
                .id(0L)
                .name("John Connor")
                .email("jconnor@gmail.com")
                .sex("MALE")
                .birthDate("1985-02-28")
                .height(165)
                .weight(65000)
                .aim("KEEPING")
                .mealList(new ArrayList<MealIdDto>())
                .build();
    }
}
