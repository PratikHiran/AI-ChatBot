package com.example.springaichatbot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles RuntimeException
     *
     * @param ex The RuntimeException
     * @return ResponseEntity with error response
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        log.error("Runtime exception occurred: ", ex);

        ErrorResponse errorResponse = new ErrorResponse(
                "Internal Server Error",
                ex.getMessage(),
                System.currentTimeMillis()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    /**
     * Handles general Exception
     *
     * @param ex The Exception
     * @return ResponseEntity with error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("Exception occurred: ", ex);

        ErrorResponse errorResponse = new ErrorResponse(
                "Error",
                ex.getMessage(),
                System.currentTimeMillis()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}

