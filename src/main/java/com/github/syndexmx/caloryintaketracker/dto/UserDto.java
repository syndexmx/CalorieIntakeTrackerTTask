package com.github.syndexmx.caloryintaketracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Пользователь")
public class UserDto {

    private Long id;

    @NotNull(message = "Name field should not be null")
    @NotBlank(message = "Name is required")
    @Schema(description = "Имя")
    private String name;

    @NotNull(message = "eMail field should not be null")
    @Email
    @NotBlank(message = "eMail is required")
    @Schema(description = "Адрес электронной почты")
    private String email;

    @NotNull(message = "Sex field should not be null")
    @NotBlank(message = "Sex is required")
    @Schema(description = "Пол")
    private String sex;

    @NotNull(message = "Birth Date field should not be null")
    @NotBlank(message = "Birth Date is required")
    @Schema(description = "Дата рождения")
    private String birthDate;

    @NotNull(message = "Weight field should not be null")
    @DecimalMin("40000.0")
    @DecimalMax("160000.0")
    @Schema(description = "Вес в граммах")
    private Integer weight; // weight in grams

    @NotNull(message = "Height field should not be null")
    @DecimalMin("120.0")
    @DecimalMax("250.0")
    @Schema(description = "Рост в сантиметрах")
    private Integer height; // height in cm

    @NotNull(message = "Aim field should not be null")
    @Schema(description = "Цель: LOOSING = снижение веса, KEEPING = поддержание веса, GAINING = набор веса")
    private String aim;
    private List<MealDto> mealList;

}