package com.github.syndexmx.caloryintaketracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String sex;
    private String birthDate;
    private Integer weight; // weight in grams
    private Integer height; // height in cm
    private String aim;

}