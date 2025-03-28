package com.github.syndexmx.caloryintaketracker.controllers;

import com.github.syndexmx.caloryintaketracker.dto.UserDto;
import com.github.syndexmx.caloryintaketracker.dto.UserShallowDto;
import com.github.syndexmx.caloryintaketracker.entities.User;
import com.github.syndexmx.caloryintaketracker.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.caloryintaketracker.dto.mappers.UserDtoMapper.*;

@RestController
@RequestMapping
@Validated
@Tag(name = "User", description = "API пользователя")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v0/users")
    @Operation(summary = "Добавление пользователя",
            description = "Позволяет добавить нового пользователя. id не важен, присваивается системой")
    public ResponseEntity<Object> createDish(@RequestBody @Validated UserDto userDto) {
        userDto.setId(null);
        User receivedUser = userDtoToUser(userDto);
        User createdUser = userService.create(receivedUser);
        UserShallowDto returnUserDto = userToUserDtoShallow(createdUser);
        return new ResponseEntity<>(returnUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/api/v0/users")
    @Operation(summary = "Получение списка пользователей",
            description = "Возвращает список всех пользователей")
    public ResponseEntity<List<UserDto>> listAllUsers() {
        List<User> userList = userService.listAll();
        List<UserDto> userDtoList = userList.stream()
                .map(user -> userToUserDto(user))
                .toList();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping("/api/v0/users/{id}")
    @Operation(summary = "Получение пользователя по id",
            description = "Возвращает данные пользователя")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        UserDto userDto = userToUserDto(optionalUser.get());
        return new ResponseEntity<>(userDto, HttpStatus.FOUND);
    }

    @PutMapping("/api/v0/users/{id}")
    @Operation(summary = "Коррекция пользователя по id",
            description = "Позволяет обновить данные пользователя, id в пути и в переданных данных должны совпадать")
    public ResponseEntity<UserShallowDto> updateEntity(@PathVariable Long id,
                                                @RequestBody @Validated UserDto userDto) {
        final User user = userDtoToUser(userDto);
        if (!id.equals(user.getId())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        if (!userService.isPresent(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Optional<User> optionalSavedUser = userService.save(user);
        if (optionalSavedUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        UserShallowDto savedUserDto = userToUserDtoShallow(optionalSavedUser.get());
        ResponseEntity<UserShallowDto> responseEntity = new ResponseEntity<UserShallowDto>(
                savedUserDto, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @DeleteMapping("/api/v0/users/{id}")
    @Operation(summary = "Удалить пользователя по id",
            description = "Удаляет данные пользователя из базы")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
