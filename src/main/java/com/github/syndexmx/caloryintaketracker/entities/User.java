package com.github.syndexmx.caloryintaketracker.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private UUID id;
    private String name;
    private String email;
    private Sexes sex;
    private Date birthDate;
    private Integer weight; // weight in grams
    private Integer height; // height in cm
    private Aims aim;

}
