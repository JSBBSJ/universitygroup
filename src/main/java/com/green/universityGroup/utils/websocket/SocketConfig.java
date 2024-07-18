package com.green.universityGroup.utils.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class SocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		// js 웹소켓 접속시
		// 엔드포인트 등록 -> 연결할 소켓 엔드포인트를 지정하는 코드입니다.
		registry.addEndpoint("/rara-bot").withSockJS();

	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 사용자->서버에 메시지보낼때 전송할때 접두사
		registry.setApplicationDestinationPrefixes("/bot");
		registry.enableSimpleBroker("/topic"); //서버 클라이언트한테 보낼때
	}
}
