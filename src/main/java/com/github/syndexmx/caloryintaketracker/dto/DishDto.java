package com.github.syndexmx.caloryintaketracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Блюдо")
public class DishDto {

    @Schema(description = "Идентификатор", example = "1")
    Long id;

    @Schema(description = "Наименование блюда", example = "Apples")
    String name;

    @NotNull(message = "Calories field should not be null")
    @Schema(description = "Калорийность порции", example = "60")
    Integer calories; // per unit

    @Schema(description = "Белки в миллиграммах на порцию", example = "400")
    Integer protein; // mg per unit

    @Schema(description = "Жиры в миллиграммах на порцию", example = "450")
    Integer lipids; // mg per unit

    @Schema(description = "Углеводы в миллиграммах на порцию", example = "9800")
    Integer carbohydrates; // mg per unit

}

