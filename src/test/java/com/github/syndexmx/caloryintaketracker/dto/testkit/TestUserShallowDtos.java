package com.github.syndexmx.caloryintaketracker.dto.testkit;

import com.github.syndexmx.caloryintaketracker.dto.MealIdDto;
import com.github.syndexmx.caloryintaketracker.dto.UserShallowDto;

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
