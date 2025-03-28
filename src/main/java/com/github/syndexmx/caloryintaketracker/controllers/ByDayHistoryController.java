package com.github.syndexmx.caloryintaketracker.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Hidden
@Tag(name = "Meals By-Day History Controller", description = "Позволяет выводить историю приемов пищи пользователя по дням")
public class ByDayHistoryController {

    @GetMapping("/api/v0/bydayreports/{userid}")
    public ResponseEntity<Object> findById(@PathVariable(name = "userid") String id) {
        // TODO : implement daily reports
        return new ResponseEntity<>("Not implemeneted", HttpStatus.NOT_IMPLEMENTED);
    }

}
