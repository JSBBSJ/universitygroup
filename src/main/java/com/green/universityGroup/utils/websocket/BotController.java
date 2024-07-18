package com.green.universityGroup.utils.websocket;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BotController {
	
	private final SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/chat.sendMessage")
	@SendTo
	public String sendMessage(String message) {
		return message;
	}

	@MessageMapping("/hello")
	public void handleHello(String message) {
		// 여기서 초기 인삿말 메시지를 처리할 수 있습니다.
		// 예를 들어, 특정 클라이언트에게 환영 메시지를 보낼 수 있습니다.
		messagingTemplate.convertAndSend("/topic/bot/" + message, "안녕하세요! 무엇을 도와드릴까요?");
	}
	
	@MessageMapping("/question")
    public void handleQuestion(String message) {
        // 여기서 질문 메시지를 처리할 수 있습니다.
        // 예를 들어, 질문에 대한 답변을 생성하여 클라이언트에게 보낼 수 있습니다.
        messagingTemplate.convertAndSend("/topic/bot/" + message, "질문에 대한 답변입니다.");
    }
}
