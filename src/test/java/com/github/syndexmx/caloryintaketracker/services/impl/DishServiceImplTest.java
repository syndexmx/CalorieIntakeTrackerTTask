package com.github.syndexmx.caloryintaketracker.services.impl;

import com.github.syndexmx.caloryintaketracker.entities.Dish;
import com.github.syndexmx.caloryintaketracker.entities.TestDishes;
import com.github.syndexmx.caloryintaketracker.repositories.DishRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        final Dish savedDish = underTest.save(dish);
        assertEquals(dish, savedDish);
    }

}
