package com.github.syndexmx.caloryintaketracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishDto {

    Long id;
    String name;
    Integer calories; // per unit
    Integer protein; // mg per unit
    Integer lipids; // mg per unit
    Integer carbohydrates; // mg per unit

}

