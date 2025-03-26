package com.github.syndexmx.caloryintaketracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MealDto {

    Long id;
    private String date;
    private List<DishDto> dishList;

}