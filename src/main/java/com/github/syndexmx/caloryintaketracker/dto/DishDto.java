package com.github.syndexmx.caloryintaketracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class DishDto {

    Long id;
    String name;

    @NotNull(message = "Calories field should not be null")
    Integer calories; // per unit

    Integer protein; // mg per unit
    Integer lipids; // mg per unit
    Integer carbohydrates; // mg per unit

}

