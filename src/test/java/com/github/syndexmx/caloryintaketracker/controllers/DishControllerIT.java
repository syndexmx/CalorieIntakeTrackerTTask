package com.github.syndexmx.caloryintaketracker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.caloryintaketracker.dto.DishDto;
import com.github.syndexmx.caloryintaketracker.dto.TestDishDtos;
import com.github.syndexmx.caloryintaketracker.entities.Dish;
import com.github.syndexmx.caloryintaketracker.entities.TestDishes;
import com.github.syndexmx.caloryintaketracker.services.DishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.github.syndexmx.caloryintaketracker.dto.mappers.DishMapper.dishDtoToDish;
import static com.github.syndexmx.caloryintaketracker.dto.mappers.DishMapper.dishToDishDto;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DishControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DishService dishService;

    @Test
    public void testThatDishIsCreated() throws Exception {
        DishDto dishDto = TestDishDtos.getTestDishDto();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String dishJson = objectMapper.writeValueAsString(dishDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v0/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dishJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(dishJson));
    }

    @Test
    public void testThatListReturnesWhenExists() throws Exception {
        DishDto dishDto = TestDishDtos.getTestDishDto();
        dishDto.setId(null);
        final Dish dishSaved = dishService.create(dishDtoToDish(dishDto));
        DishDto dishDto2 = TestDishDtos.getTestDishDto(2);
        dishDto2.setId(null);
        final Dish dishSaved2 = dishService.create(dishDtoToDish(dishDto2));
        final List<DishDto> listDishDto = new ArrayList<>(List.of(
                dishToDishDto(dishSaved),
                dishToDishDto(dishSaved2)));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String dishListJson = objectMapper.writeValueAsString(listDishDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v0/dishes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(dishListJson));
    }

    @Test
    public void testThatRetrieveReturnsNotFoundWhenAbsent() throws Exception {
        final Long nonExistentId = Long.MIN_VALUE;
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v0/generics/" + nonExistentId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsDishWhenExists() throws Exception {
        Dish dish = TestDishes.getTestDish();
        dish.setId(null);
        final Dish dishSaved = dishService.create(dish);
        final long id = dishSaved.getId();
        final DishDto savedDishDto = dishToDishDto(dishSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String genericJson = objectMapper.writeValueAsString(savedDishDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v0/dishes/" + id))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(genericJson));
    }

}
