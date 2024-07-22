package com.green.universityGroup.utils.websocket;

import java.util.List;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.green.universityGroup.domain.dto.ChatbotAnswerListDTO;

import com.green.universityGroup.service.ChatbotEntityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BotController {

	private final SimpMessagingTemplate messagingTemplate;
	private final ChatbotEntityService service;

	@GetMapping("/chats")
	public List<ChatbotAnswerListDTO> getAllQuestions() {
		return service.getAllChatbotEntries();
	}

	// 클라이언트에서 카테고리 요청을 받는 메서드
	@MessageMapping("/category/{category}")
	@SendTo("/topic/public")
	public List<ChatbotAnswerListDTO> getCategoryList(@PathVariable String category) {
		return service.getCategoryListProcess(category);
	}

	// 클라이언트에서 질문 요청을 받는 메서드
	@MessageMapping("/chatbot/{chatbot_no}")
	@SendTo("/topic/public")
	public ChatbotAnswerListDTO getChatbotContentList(@PathVariable Long chatbot_no) {
		// 해당 질문에 대한 답변을 조회합니다.
		return service.getChatbotContentListProcess(chatbot_no);
	}
}
