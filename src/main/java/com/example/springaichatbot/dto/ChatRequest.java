package com.example.springaichatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for chat messages
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}

