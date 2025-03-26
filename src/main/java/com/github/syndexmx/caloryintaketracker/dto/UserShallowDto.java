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
public class UserShallowDto {

    private Long id;
    private String name;
    private String email;
    private String sex;
    private String birthDate;
    private Integer weight; // weight in grams
    private Integer height; // height in cm
    private String aim;
    private List<MealIdDto> mealList;

}