package com.chungxangla.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chungxangla.demo.model.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    public ResponseEntity<ApiResponse<?>> handleNotFound(ResourceNotFoundException ex){
        return new ResponseEntity<>(new ApiResponse<>(false, ex.getMessage(), null),
        HttpStatus.NOT_FOUND);
    }
}
