package com.example.springaichatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for AI generated answers
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

