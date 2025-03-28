package com.github.syndexmx.caloryintaketracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MealDto {

    @Schema(description = "Идентификатор", example = "1")
    Long id;

    @NotNull(message = "Date field should not be null")
    @NotBlank(message = "Date is required")
    @Schema(description = "Дата", example = "2025-01-01")
    private String date;

    @Schema(description = "Список блюд")
    private List<DishDto> dishList;

}