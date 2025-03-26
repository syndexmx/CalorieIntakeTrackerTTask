package com.github.syndexmx.caloryintaketracker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DailyReportController {

    @GetMapping("/api/v0/dailyreports/{userid}")
    public ResponseEntity<Object> findById(@PathVariable(name = "userid") String id,
        @PathVariable(name = "date") String dateString) {
        // TODO : implement daily reports
        return new ResponseEntity<>("Not implemeneted", HttpStatus.NOT_IMPLEMENTED);
    }

}
