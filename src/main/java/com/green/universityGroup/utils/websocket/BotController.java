package com.green.universityGroup.utils.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.universityGroup.domain.dto.ChatMessageDto;
import com.green.universityGroup.service.ChatbotService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BotController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatbotService service;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(ChatMessageDto messageDto) {
        String responseMessage = service.getAnswerForQuestion(messageDto.getChatContent());
        messagingTemplate.convertAndSend("/topic/bot/" + messageDto.getKey(), "{\"answer\":\"" + responseMessage + "\"}");
    }

    @MessageMapping("/hello")
    public void handleHello(ChatMessageDto messageDto) {
        messagingTemplate.convertAndSend("/topic/bot/" + messageDto.getKey(), "{\"answer\":\"안녕하세요! 무엇을 도와드릴까요?\"}");
    }

    @MessageMapping("/question")
    public void handleQuestion(ChatMessageDto messageDto) {
        String answer = service.getAnswerForQuestion(messageDto.getChatContent());
        messagingTemplate.convertAndSend("/topic/bot/" + messageDto.getKey(), "{\"answer\":\"" + answer + "\"}");
    }

    @GetMapping("/chatbot/questions")
    public String getQuestions(Model model) {
        return service.getAllQuestions(model);
    }
}
