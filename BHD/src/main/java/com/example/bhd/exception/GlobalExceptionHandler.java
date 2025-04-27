package com.example.bhd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex, WebRequest request) {
        ErrorMessage errorDetails = new ErrorMessage(ex.getStatusCode(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(ex.getStatusCode()));
    }
}
