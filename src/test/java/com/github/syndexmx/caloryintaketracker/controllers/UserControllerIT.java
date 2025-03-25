package com.github.syndexmx.caloryintaketracker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.caloryintaketracker.dto.DishDto;
import com.github.syndexmx.caloryintaketracker.dto.TestDishDtos;
import com.github.syndexmx.caloryintaketracker.dto.TestUserDtos;
import com.github.syndexmx.caloryintaketracker.dto.UserDto;
import com.github.syndexmx.caloryintaketracker.entities.TestUsers;
import com.github.syndexmx.caloryintaketracker.entities.User;
import com.github.syndexmx.caloryintaketracker.services.UserService;
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

import static com.github.syndexmx.caloryintaketracker.dto.mappers.UserDtoMapper.userDtoToUser;
import static com.github.syndexmx.caloryintaketracker.dto.mappers.UserDtoMapper.userToUserDto;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void testThatUserIsCreated() throws Exception {
        UserDto userDto = TestUserDtos.getTestUserDto(1);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String userJson = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v0/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(userJson));
    }

    @Test
    public void testThatListReturnesWhenExists() throws Exception {
        UserDto userDto = TestUserDtos.getTestUserDto();
        userDto.setId(null);
        final User userSaved = userService.create(userDtoToUser(userDto));
        UserDto userDto2 = TestUserDtos.getTestUserDto(2);
        userDto2.setId(null);
        final User userSaved2 = userService.create(userDtoToUser(userDto2));
        final List<UserDto> listUserDto = new ArrayList<>(List.of(
                userToUserDto(userSaved),
                userToUserDto(userSaved2)));
        final ObjectMapper objectMapper = new ObjectMapper();
        final String userListJson = objectMapper.writeValueAsString(listUserDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v0/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(userListJson));
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
        User user = TestUsers.getTestUser();
        user.setId(null);
        final User userSaved = userService.create(user);
        final long id = userSaved.getId();
        final UserDto savedUserDto = userToUserDto(userSaved);
        user.setId(id);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String genericJson = objectMapper.writeValueAsString(userToUserDto(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v0/users/" + id))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(genericJson));
    }

    @Test
    public void testThatUpdateReturnsNotFoundWhenAbsent() throws Exception {
        final UserDto userDto = TestUserDtos.getTestUserDto(1000);
        final Long pathVariableId = userDto.getId();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String userJson = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v0/users/" + pathVariableId, userDto)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatUpdateReturnsConflictWhenIdMismatch() throws Exception {
        final UserDto userDto = TestUserDtos.getTestUserDto();
        final DishDto dishDto2 = TestDishDtos.getTestDishDto(2);
        final Long pathVariableId = dishDto2.getId();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String userJson = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v0/users/" + pathVariableId, userDto)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    public void testThatUpdateWorks() throws Exception {
        UserDto userDto = TestUserDtos.getTestUserDto();
        userDto.setId(null);
        User savedUser = userService.create(userDtoToUser(userDto));
        UserDto updatedUserDto = TestUserDtos.getTestUserDto(2);
        final Long id = savedUser.getId();
        updatedUserDto.setId(id);
        final ObjectMapper updatedObjectMapper = new ObjectMapper();
        final String updatedUserJson = updatedObjectMapper.writeValueAsString(updatedUserDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v0/users/" + id, updatedUserDto)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJson))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().json(updatedUserJson));
    }

    @Test
    public void testThatDeleteWorks() throws Exception {
        UserDto userDto = TestUserDtos.getTestUserDto();
        userDto.setId(null);
        User savedUser = userService.create(userDtoToUser(userDto));
        UserDto updatedUserDto = TestUserDtos.getTestUserDto(2);
        final Long id = savedUser.getId();
        updatedUserDto.setId(id);
        final ObjectMapper updatedObjectMapper = new ObjectMapper();
        final String updatedUserJson = updatedObjectMapper.writeValueAsString(updatedUserDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v0/users/" + id, updatedUserDto))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}