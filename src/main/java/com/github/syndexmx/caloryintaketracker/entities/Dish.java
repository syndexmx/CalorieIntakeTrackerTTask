package com.github.syndexmx.caloryintaketracker.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    Long id;

    String name;

    Integer calories; // per unit

    Integer protein; // mg per unit

    Integer lipids; // mg per unit

    Integer carbohydrates; // mg per unit

}
