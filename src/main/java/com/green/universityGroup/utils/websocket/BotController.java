package com.green.universityGroup.utils.websocket;


import java.util.List;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.universityGroup.domain.entity.ChatbotEntity;
import com.green.universityGroup.service.ChatbotEntityService;
import com.green.universityGroup.service.impl.ChatbotEntityServiceProcess;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BotController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatbotEntityService service;

    @GetMapping("/chats")
    public List<ChatbotEntity> getAllQuestions() {
        return service.getAllChatbotEntries();
    }

    @MessageMapping("/answer/{questionId}")
    public void handleAnswer(@DestinationVariable Long questionId) {
        String answer = service.getAnswerByQuestionId(questionId);
        messagingTemplate.convertAndSend("/topic/bot/response", answer);
    }
}
