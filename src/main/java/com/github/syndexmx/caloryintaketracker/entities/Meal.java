package com.github.syndexmx.caloryintaketracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private LocalDate date;

    @ManyToMany
    private List<Dish> dishList;

    public Integer getCalories() {
        int calories = 0;
        for (Dish dish : dishList) {
            calories += dish.getCalories();
        }
        return calories;
    }

}
