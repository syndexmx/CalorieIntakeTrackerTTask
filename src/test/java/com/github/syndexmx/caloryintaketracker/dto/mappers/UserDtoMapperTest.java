package com.github.syndexmx.caloryintaketracker.dto.mappers;

import com.github.syndexmx.caloryintaketracker.dto.*;
import com.github.syndexmx.caloryintaketracker.dto.testkit.TestUserDtos;
import com.github.syndexmx.caloryintaketracker.dto.testkit.TestUserShallowDtos;
import com.github.syndexmx.caloryintaketracker.entities.Meal;
import com.github.syndexmx.caloryintaketracker.entities.testkit.TestMeals;
import com.github.syndexmx.caloryintaketracker.entities.testkit.TestUsers;
import com.github.syndexmx.caloryintaketracker.entities.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.syndexmx.caloryintaketracker.dto.mappers.UserDtoMapper.*;
import static org.junit.jupiter.api.Assertions.*;

class UserDtoMapperTest {

    @Test
    void testUserDtoToUser() {
        UserDto userDto = TestUserDtos.getTestUserDto();
        User expected = TestUsers.getTestUser();
        User actual = userDtoToUser(userDto);
        assertEquals(expected, actual);
    }

    @Test
    void testUserToUserDto() {
        User user = TestUsers.getTestUser();
        UserDto expected = TestUserDtos.getTestUserDto();
        UserDto actual = userToUserDto(user);
        assertEquals(expected, actual);
    }

    @Test
    void testUserToUserDtoShallow() {
        User user = TestUsers.getTestUser();
        Meal meal = TestMeals.getTestMeal(2);
        List<Meal> expectedMealList = new ArrayList<Meal>();
        expectedMealList.add(meal);
        user.setMealList(expectedMealList);
        Long mealId = meal.getId();
        UserShallowDto expected = TestUserShallowDtos.getTestUserShallowDto();
        List<MealIdDto> expectedMealDtoList = new ArrayList<MealIdDto>();
        expectedMealDtoList.add(MealIdDto.builder().id(mealId).build());
        expected.setMealList(expectedMealDtoList);
        UserShallowDto actual = userToUserDtoShallow(user);
        assertEquals(expected, actual);
    }

}