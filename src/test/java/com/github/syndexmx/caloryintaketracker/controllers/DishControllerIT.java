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

}
