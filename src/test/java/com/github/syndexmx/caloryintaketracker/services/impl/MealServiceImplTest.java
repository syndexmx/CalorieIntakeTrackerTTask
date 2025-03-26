package com.github.syndexmx.caloryintaketracker.services.impl;

import com.github.syndexmx.caloryintaketracker.entities.Meal;
import com.github.syndexmx.caloryintaketracker.entities.testkit.TestMeals;
import com.github.syndexmx.caloryintaketracker.repositories.MealRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MealServiceImplTest {

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private MealServiceImpl underTest;

    @Test
    public void testThatMealIsCreated() {
        final Meal meal = TestMeals.getTestMeal();
        when(mealRepository.save(eq(meal))).thenReturn(meal);
        final Meal savedUser = underTest.create(meal);
        assertEquals(meal, savedUser);
    }

    @Test
    public void testThatListIsReturnedWhenExist() {
        final Meal meal = TestMeals.getTestMeal();
        final Meal meal2 = TestMeals.getTestMeal(2);
        List<Meal> listOfExisting = new ArrayList<>(List.of(meal, meal2));
        when(mealRepository.findAll()).thenReturn(listOfExisting);
        final List<Meal> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
        for (Meal d : listOfExisting) {
            boolean isFound = false;
            for (Meal r : result) {
                if (d.equals(r)) {
                    isFound = true;
                }
            }
            assertTrue(isFound);
        }
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Meal nonExistentMeal = TestMeals.getTestMeal();
        final Long nonExistentId = Long.MIN_VALUE;
        when(mealRepository.findById(eq(nonExistentId))).thenReturn(Optional.empty());
        final Optional<Meal> foundOptionalMeal = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundOptionalMeal);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Meal meal = TestMeals.getTestMeal();
        final Long id = meal.getId();
        when(mealRepository.findById(eq(id))).thenReturn(Optional.of(meal));
        final Optional<Meal> optionalFoundMeal = underTest.findById(id);
        assertEquals(Optional.of(meal), optionalFoundMeal);
    }

}