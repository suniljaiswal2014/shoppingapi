package com.wheatroot.shoppingapi.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<APIResponse> handleResourceNotFound(Exception e) {
        APIResponse apiResponse = new APIResponse(false, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(value = { DataIntegrityViolationException.class })
    public ResponseEntity<APIResponse> handleDataIntegrityViolation(Exception e) {
        APIResponse apiResponse = new APIResponse(false, "Validation failed: Duplicate data found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    //Add additional methods to handle other specific exceptions as needed

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<APIResponse> handleInternalServerError(Exception e) {
        // Log the error for debugging
        e.printStackTrace();
        APIResponse apiResponse = new APIResponse(false, "Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
