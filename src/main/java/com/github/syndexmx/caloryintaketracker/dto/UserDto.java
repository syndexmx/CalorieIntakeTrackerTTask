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
    @Schema(description = "Имя", example = "John Connor")
    private String name;

    @NotNull(message = "eMail field should not be null")
    @Email
    @NotBlank(message = "eMail is required")
    @Schema(description = "Адрес электронной почты", example = "connor_john1985@gmail.com")
    private String email;

    @NotNull(message = "Sex field should not be null")
    @NotBlank(message = "Sex is required")
    @Schema(description = "Пол", example = "MALE")
    private String sex;

    @NotNull(message = "Birth Date field should not be null")
    @NotBlank(message = "Birth Date is required")
    @Schema(description = "Дата рождения", example = "1985-02-28")
    private String birthDate;

    @NotNull(message = "Weight field should not be null")
    @DecimalMin("40000.0")
    @DecimalMax("160000.0")
    @Schema(description = "Вес в граммах", example = "71500")
    private Integer weight; // weight in grams

    @NotNull(message = "Height field should not be null")
    @DecimalMin("120.0")
    @DecimalMax("250.0")
    @Schema(description = "Рост в сантиметрах", example = "173")
    private Integer height; // height in cm

    @NotNull(message = "Aim field should not be null")
    @Schema(description = "Цель: LOOSING = снижение веса, KEEPING = поддержание веса, GAINING = набор веса",
            example = "GAINING")
    private String aim;

    @Schema(description = "Список приемов пищи")
    private List<MealDto> mealList;

}