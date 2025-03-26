package com.github.syndexmx.caloryintaketracker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.caloryintaketracker.dto.DishDto;
import com.github.syndexmx.caloryintaketracker.dto.testkit.TestDishDtos;
import com.github.syndexmx.caloryintaketracker.entities.Dish;
import com.github.syndexmx.caloryintaketracker.entities.testkit.TestDishes;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.github.syndexmx.caloryintaketracker.dto.mappers.DishDtoMapper.dishDtoToDish;
import static com.github.syndexmx.caloryintaketracker.dto.mappers.DishDtoMapper.dishToDishDto;

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
        DishDto dishDto = TestDishDtos.getTestDishDto(1);
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
                MockMvcRequestBuilders.get("/api/v0/users/" + nonExistentId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveReturnsDishWhenExists() throws Exception {
        Dish dish = TestDishes.getTestDish();
        dish.setId(null);
        final Dish dishSaved = dishService.create(dish);
        final long id = dishSaved.getId();
        final DishDto savedDishDto = dishToDishDto(dishSaved);
        dish.setId(id);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String genericJson = objectMapper.writeValueAsString(dishToDishDto(dish));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v0/dishes/" + id))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(genericJson));
    }

    @Test
    public void testThatUpdateReturnsNotFoundWhenAbsent() throws Exception {
        final DishDto dishDto = TestDishDtos.getTestDishDto(1000);
        final Long pathVariableId = dishDto.getId();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String dishJson = objectMapper.writeValueAsString(dishDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v0/dishes/" + pathVariableId, dishDto)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dishJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatUpdateReturnsConflictWhenIdMismatch() throws Exception {
        final DishDto dishDto = TestDishDtos.getTestDishDto();
        final DishDto dishDto2 = TestDishDtos.getTestDishDto(2);
        final Long pathVariableId = dishDto2.getId();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String dishJson = objectMapper.writeValueAsString(dishDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v0/dishes/" + pathVariableId, dishDto)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(dishJson))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    public void testThatUpdateWorks() throws Exception {
        DishDto dishDto = TestDishDtos.getTestDishDto();
        dishDto.setId(null);
        Dish savedDish = dishService.create(dishDtoToDish(dishDto));
        DishDto updatedDishDto = TestDishDtos.getTestDishDto(2);
        final Long id = savedDish.getId();
        updatedDishDto.setId(id);
        final ObjectMapper updatedObjectMapper = new ObjectMapper();
        final String updatedDishJson = updatedObjectMapper.writeValueAsString(updatedDishDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v0/dishes/" + id, updatedDishDto)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedDishJson))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().json(updatedDishJson));
    }

    @Test
    public void testThatDeleteWorks() throws Exception {
        DishDto dishDto = TestDishDtos.getTestDishDto();
        dishDto.setId(null);
        Dish savedDish = dishService.create(dishDtoToDish(dishDto));
        DishDto updatedDishDto = TestDishDtos.getTestDishDto(2);
        final Long id = savedDish.getId();
        updatedDishDto.setId(id);
        final ObjectMapper updatedObjectMapper = new ObjectMapper();
        final String updatedDishJson = updatedObjectMapper.writeValueAsString(updatedDishDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v0/dishes/" + id, updatedDishDto))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
