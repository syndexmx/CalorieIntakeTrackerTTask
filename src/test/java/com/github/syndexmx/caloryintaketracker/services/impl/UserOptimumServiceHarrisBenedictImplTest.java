package com.github.syndexmx.caloryintaketracker.services.impl;

import com.github.syndexmx.caloryintaketracker.entities.TestUsers;
import com.github.syndexmx.caloryintaketracker.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserOptimumServiceHarrisBenedictImplTest {

    @InjectMocks
    private UserOptimumServiceHarrisBenedictImpl underTest;

    @Test
    void testThatCalculationIsOk() {
        User user = TestUsers.getTestUser();
        int calculatedByService = underTest
                .getOptimalDailyCalorieIntake(user, LocalDate.parse("2025-01-01"));
        System.out.println(calculatedByService);
        int expected = 716;
        assertEquals(expected, calculatedByService);
    }

}