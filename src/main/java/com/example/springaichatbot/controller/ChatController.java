package com.example.springaichatbot.controller;

import com.example.springaichatbot.dto.ChatRequest;
import com.example.springaichatbot.dto.ChatResponse;
import com.example.springaichatbot.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for chat operations
 */
@Slf4j
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    /**
     * Endpoint to send a message and receive an AI-generated response
     *
     * @param request The chat request containing the user's message
     * @return ResponseEntity containing the chat response with AI's answer
     */
    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        log.info("Chat request received with message: {}", request.getMessage());

        String answer = chatService.chat(request.getMessage());
        ChatResponse response = new ChatResponse(answer);

        return ResponseEntity.ok(response);
    }
}

