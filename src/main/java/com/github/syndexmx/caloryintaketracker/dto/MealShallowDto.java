package com.github.syndexmx.caloryintaketracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MealShallowDto {

    Long id;

    @NotNull(message = "Date field should not be null")
    @NotBlank(message = "Date is required")
    private String date;

    private List<DishIdDto> dishList;

}