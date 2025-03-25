package com.github.syndexmx.caloryintaketracker.services.impl;

import com.github.syndexmx.caloryintaketracker.entities.Dish;
import com.github.syndexmx.caloryintaketracker.entities.TestDishes;
import com.github.syndexmx.caloryintaketracker.entities.TestUsers;
import com.github.syndexmx.caloryintaketracker.entities.User;
import com.github.syndexmx.caloryintaketracker.repositories.DishRepository;
import com.github.syndexmx.caloryintaketracker.repositories.UserRepository;
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
public class DishServiceImplTest {

    @Mock
    private DishRepository dishRepository;

    @InjectMocks
    private DishServiceImpl underTest;

    @Test
    public void testThatDishIsCreated() {
        final Dish dish = TestDishes.getTestDish();
        when(dishRepository.save(eq(dish))).thenReturn(dish);
        final Dish savedDish = underTest.create(dish);
        assertEquals(dish, savedDish);
    }

    @Test
    public void testThatListIsReturnedWhenExist() {
        final Dish dish = TestDishes.getTestDish();
        final Dish dish2 = TestDishes.getTestDish(2);
        List<Dish> listOfExisting = new ArrayList<>(List.of(dish, dish2));
        when(dishRepository.findAll()).thenReturn(listOfExisting);
        final List<Dish> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
        for (Dish d : listOfExisting) {
            boolean isFound = false;
            for (Dish r : result) {
                if (d.equals(r)) {
                    isFound = true;
                }
            }
            assertTrue(isFound);
        }
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Dish nonExistentGeneric = TestDishes.getTestDish();
        final Long nonExistentId = Long.MIN_VALUE;
        when(dishRepository.findById(eq(nonExistentId))).thenReturn(Optional.empty());
        final Optional<Dish> foundOptionalDish = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundOptionalDish);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Dish dish = TestDishes.getTestDish();
        final Long id = dish.getId();
        when(dishRepository.findById(eq(id))).thenReturn(Optional.of(dish));
        final Optional<Dish> foundGeneric = underTest.findById(id);
        assertEquals(Optional.of(dish), foundGeneric);
    }

    /*
    @Test
    public void testThatDishIsSaved() {
        final Dish dish = TestDishes.getTestDish();
        final Optional<Dish> optionalDish = Optional.of(dish);
        when(dishRepository.save(eq(dish))).thenReturn(Optional.of(dish));
        final Optional<Dish> optionalSavedDish = underTest.save(dish);
        final Dish savedDish = optionalSavedDish.get();
        assertEquals(dish, savedDish);
    }
    */


}
