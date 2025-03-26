package com.github.syndexmx.caloryintaketracker.controllers;

import com.github.syndexmx.caloryintaketracker.dto.MealDto;
import com.github.syndexmx.caloryintaketracker.dto.MealShallowDto;
import com.github.syndexmx.caloryintaketracker.entities.Meal;
import com.github.syndexmx.caloryintaketracker.services.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.github.syndexmx.caloryintaketracker.dto.mappers.MealDtoMapper.*;

@RestController
@RequestMapping
@Validated
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping("/api/v0/meals")
    public ResponseEntity<MealShallowDto> createDish(@RequestBody @Validated MealDto mealDto) {
        mealDto.setId(null);
        Meal createdMeal = mealService.create(mealDtoToMeal(mealDto));
        MealShallowDto returnMealDto = mealToMealDtoShallow(createdMeal);
        return new ResponseEntity<>(returnMealDto, HttpStatus.CREATED);
    }

    @GetMapping("/api/v0/meals")
    public ResponseEntity<List<MealDto>> listAllDishes() {
        List<Meal> mealList = mealService.listAll();
        List<MealDto> mealDtoList = mealList.stream()
                .map(meal -> mealToMealDto(meal))
                .toList();
        return new ResponseEntity<>(mealDtoList, HttpStatus.OK);
    }

    @GetMapping("/api/v0/meals/{id}")
    public ResponseEntity<MealDto> findById(@PathVariable Long id) {
        Optional<Meal> optionalMeal = mealService.findById(id);
        if (optionalMeal.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        MealDto mealDto = mealToMealDto(optionalMeal.get());
        return new ResponseEntity<>(mealDto, HttpStatus.FOUND);
    }

    @PutMapping("/api/v0/meals/{id}")
    public ResponseEntity<MealShallowDto> updateEntity(@PathVariable Long id,
                                                @RequestBody @Validated MealDto mealDto) {
        final Meal meal = mealDtoToMeal(mealDto);
        if (!id.equals(meal.getId())) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        if (!mealService.isPresent(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Optional<Meal> optionalSavedMeal = mealService.save(meal);
        if (optionalSavedMeal.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        MealShallowDto savedMealDto = mealToMealDtoShallow(optionalSavedMeal.get());
        ResponseEntity<MealShallowDto> responseEntity = new ResponseEntity<MealShallowDto>(
                savedMealDto, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @DeleteMapping("/api/v0/meals/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        mealService.deleteById(id);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}

