package com.example.springaichatbot.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Error response DTO for standardized error responses
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String error;
    private String message;
    private long timestamp;
}

