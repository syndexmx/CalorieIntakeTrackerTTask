package com.github.syndexmx.caloryintaketracker.advices;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        return error.getDefaultMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder messageBuilder = new StringBuilder();
        e.getConstraintViolations()
                .forEach(constraintViolation
                        -> messageBuilder.append(constraintViolation.getPropertyPath().toString()
                        + " : " + constraintViolation.getMessage() + "\n"));
        ErrorResponse response = ErrorResponse.create(
                new RuntimeException(messageBuilder.toString()),
                HttpStatus.BAD_REQUEST,
                messageBuilder.toString());
        return response;
    }

}
