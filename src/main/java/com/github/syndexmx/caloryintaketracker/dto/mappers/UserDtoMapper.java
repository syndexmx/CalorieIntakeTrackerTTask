package com.github.syndexmx.caloryintaketracker.dto.mappers;

import com.github.syndexmx.caloryintaketracker.dto.UserDto;
import com.github.syndexmx.caloryintaketracker.dto.UserShallowDto;
import com.github.syndexmx.caloryintaketracker.entities.Aims;
import com.github.syndexmx.caloryintaketracker.entities.Sexes;
import com.github.syndexmx.caloryintaketracker.entities.User;

import java.time.LocalDate;

import static com.github.syndexmx.caloryintaketracker.dto.mappers.MealDtoMapper.*;

public class UserDtoMapper {

    public static User userDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .birthDate(LocalDate.parse(userDto.getBirthDate()))
                .sex(Sexes.valueOf(userDto.getSex()))
                .height(userDto.getHeight())
                .weight(userDto.getWeight())
                .aim(Aims.valueOf(userDto.getAim()))
                .mealList(userDto.getMealList().stream()
                        .map(mealDto -> mealDtoToMealIdOnly(mealDto))
                        .toList())
                .build();
    }

    public static UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate().toString())
                .sex(user.getSex().toString())
                .height(user.getHeight())
                .weight(user.getWeight())
                .aim(user.getAim().toString())
                .mealList(user.getMealList().stream()
                        .map(meal -> mealToMealDto(meal))
                        .toList())
                .build();
    }

    public static UserShallowDto userToUserDtoShallow(User user) {
        return UserShallowDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate().toString())
                .sex(user.getSex().toString())
                .height(user.getHeight())
                .weight(user.getWeight())
                .aim(user.getAim().toString())
                .mealList(user.getMealList().stream()
                        .map(meal -> mealToMealDtoIdOnly(meal))
                        .toList())
                .build();
    }
}
