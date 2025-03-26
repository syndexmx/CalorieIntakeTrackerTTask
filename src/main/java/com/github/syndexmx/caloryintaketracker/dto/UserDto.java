package com.github.syndexmx.caloryintaketracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;

    @NotNull(message = "Name field should not be null")
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "eMail field should not be null")
    @Email
    @NotBlank(message = "eMail is required")
    private String email;

    @NotNull(message = "Sex field should not be null")
    @NotBlank(message = "Sex is required")
    private String sex;

    @NotNull(message = "Birth Date field should not be null")
    @NotBlank(message = "Birth Date is required")
    private String birthDate;

    @NotNull(message = "Weight field should not be null")
    @DecimalMin("40000.0")
    @DecimalMax("160000.0")
    private Integer weight; // weight in grams

    @NotNull(message = "Height field should not be null")
    @DecimalMin("120.0")
    @DecimalMax("250.0")
    private Integer height; // height in cm

    @NotNull(message = "Aim field should not be null")
    private String aim;
    private List<MealDto> mealList;

}