package com.github.syndexmx.caloryintaketracker.dto.validators;

import com.github.syndexmx.caloryintaketracker.dto.UserDto;

public class UserDtoValidator {

    public static void validateUserDto(UserDto userDto) {
        if (userDto.getHeight() < 120 || userDto.getHeight() > 250) {
            throw new RuntimeException("Validation error: height is outside of the allowed range.");
        }
        if (userDto.getWeight() < 40000 || userDto.getWeight() > 150000) {
            throw new RuntimeException("Validation error: height is outside of the allowed range.");
        }
    }

}
