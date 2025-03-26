package com.github.syndexmx.caloryintaketracker.dto.mappers;

import com.github.syndexmx.caloryintaketracker.dto.DishDto;
import com.github.syndexmx.caloryintaketracker.dto.TestDishDtos;
import com.github.syndexmx.caloryintaketracker.entities.Dish;
import com.github.syndexmx.caloryintaketracker.entities.TestDishes;
import org.junit.jupiter.api.Test;

import static com.github.syndexmx.caloryintaketracker.dto.mappers.DishDtoMapper.dishDtoToDish;
import static com.github.syndexmx.caloryintaketracker.dto.mappers.DishDtoMapper.dishToDishDto;
import static org.junit.jupiter.api.Assertions.*;

class DishDtoMapperTest {

    @Test
    void testDishDtoToDish() {
        DishDto dishDto = TestDishDtos.getTestDishDto();
        Dish expected = TestDishes.getTestDish();
        Dish actual = dishDtoToDish(dishDto);
        assertEquals(expected, actual);
    }

    @Test
    void testDishToDishDto() {
        Dish dish = TestDishes.getTestDish();
        DishDto expected = TestDishDtos.getTestDishDto();
        DishDto actual = dishToDishDto(dish);
        assertEquals(expected, actual);
    }
}