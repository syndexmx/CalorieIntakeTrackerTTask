package com.github.syndexmx.caloryintaketracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "dishes")
public class Dish {

    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Integer calories; // per unit

    Integer protein; // mg per unit

    Integer lipids; // mg per unit

    Integer carbohydrates; // mg per unit

}
