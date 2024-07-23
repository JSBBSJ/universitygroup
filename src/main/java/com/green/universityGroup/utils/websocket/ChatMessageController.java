package com.green.universityGroup.utils.websocket;

import com.green.universityGroup.service.QuestionService;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final QuestionService service;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String handleChatMessage(String message) {
        if ("GET_TOPICS".equals(message)) {
            // 기본 카테고리 리스트 반환
            return String.join(", ", service.getAllCategories());
        }

        // 메시지 파싱
        String[] parts = message.split(":");
        if (parts.length == 1) {
            String category = parts[0].trim();
            return service.getQuestionsByCategory(category).stream()
                    .map(q -> q.getText())
                    .collect(Collectors.joining(", "));
        } else if (parts.length == 2) {
            String category = parts[0].trim();
            String text = parts[1].trim();
            return service.getAnswer(category, text);
        }

        return "Invalid message format.";
    }
}
