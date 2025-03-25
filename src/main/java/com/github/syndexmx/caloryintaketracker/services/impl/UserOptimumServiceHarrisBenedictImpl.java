package com.github.syndexmx.caloryintaketracker.services.impl;

import com.github.syndexmx.caloryintaketracker.entities.Sexes;
import com.github.syndexmx.caloryintaketracker.entities.User;
import com.github.syndexmx.caloryintaketracker.services.UserOptimumService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserOptimumServiceHarrisBenedictImpl implements UserOptimumService {

    @Override
    public Integer getOptimalDailyCalorieIntake(User user, LocalDate date) {
        if (user.getSex() == Sexes.MALE) {
            return 66 + (13750 * user.getWeight()) + (5003 * user.getHeight() / 1000) -
                    (6775 * user.getAgeAtDate(date) / 1000);
            } else {
            return 655 + (9563 * user.getWeight() / 1000) + (1850 * user.getHeight() / 1000) -
                    (4676 * user.getAgeAtDate(date) / 1000);
            }
        }
}
