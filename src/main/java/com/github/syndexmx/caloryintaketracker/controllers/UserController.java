package com.github.syndexmx.caloryintaketracker.controllers;

import com.github.syndexmx.caloryintaketracker.dto.UserDto;
import com.github.syndexmx.caloryintaketracker.entities.User;
import com.github.syndexmx.caloryintaketracker.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.caloryintaketracker.dto.mappers.UserDtoMapper.userDtoToUser;
import static com.github.syndexmx.caloryintaketracker.dto.mappers.UserDtoMapper.userToUserDto;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v0/users")
    public ResponseEntity<UserDto> createDish(@RequestBody UserDto userDto) {
        userDto.setId(null);
        User receivedUser = userDtoToUser(userDto);
        User createdUser = userService.create(receivedUser);
        UserDto returnUserDto = userToUserDto(createdUser);
        return new ResponseEntity<>(returnUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/api/v0/users")
    public ResponseEntity<List<UserDto>> listAllUsers() {
        List<User> userList = userService.listAll();
        List<UserDto> userDtoList = userList.stream()
                .map(user -> userToUserDto(user))
                .toList();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping("/api/v0/users/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        UserDto userDto = userToUserDto(optionalUser.get());
        return new ResponseEntity<>(userDto, HttpStatus.FOUND);
    }

    @PutMapping("/api/v0/users/{id}")
    public ResponseEntity<UserDto> updateEntity(@PathVariable Long id,
                                                @RequestBody UserDto userDto) {
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
        UserDto savedUserDto = userToUserDto(optionalSavedUser.get());
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<UserDto>(
                savedUserDto, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @DeleteMapping("/api/v0/users/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
