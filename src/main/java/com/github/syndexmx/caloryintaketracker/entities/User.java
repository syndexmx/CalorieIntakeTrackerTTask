package com.github.syndexmx.caloryintaketracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Sexes sex;
    private LocalDate birthDate;
    private Integer weight; // weight in grams
    private Integer height; // height in cm
    private Aims aim;

    public int getAgeAtDate(LocalDate localDate) {
        return Period.between(birthDate, localDate).getYears();
    }

}
