package com.example.springaichatbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

/**
 * Service for handling chat interactions with AI model
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatModel chatModel;

    /**
     * Sends a message to the AI model and returns the response
     *
     * @param message The user's question or message
     * @return The AI-generated answer
     */
    public String chat(String message) {
        log.info("Received chat message: {}", message);

        try {
            Prompt prompt = new Prompt(message);
            String response = chatModel.call(prompt).getResult().getOutput().getContent();

            log.info("AI response: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Error communicating with AI model", e);
            throw new RuntimeException("Failed to get response from AI model: " + e.getMessage(), e);
        }
    }
}

