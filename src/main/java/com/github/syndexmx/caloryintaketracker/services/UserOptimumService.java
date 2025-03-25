package com.github.syndexmx.caloryintaketracker.services;

import com.github.syndexmx.caloryintaketracker.entities.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface UserOptimumService {

    public Integer getOptimalDailyCalorieIntake(User user, LocalDate date);
}
