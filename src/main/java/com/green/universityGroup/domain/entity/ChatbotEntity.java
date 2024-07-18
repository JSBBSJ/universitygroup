package com.green.universityGroup.domain.entity;

import java.util.function.Function;

import org.hibernate.annotations.DynamicUpdate;

import com.green.universityGroup.domain.dto.ChatMessageDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
@Table(name = "chatbot")
@Entity
public class ChatbotEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long chatbot_no;

	@Column(columnDefinition = "varchar(100)", nullable = false)
	private String chatTitle;

	@Column(columnDefinition = "text", nullable = false)
	private String chatContent; // 데이터베이스와 맞추기 위해 필드 이름은 'chat_content'로 유지

	@Column(columnDefinition = "text")
	private String chatAnswer; // 질문에 대한 답변
	
	public ChatMessageDto toChatMessageListDTO() {
		return ChatMessageDto.builder()
				.chatTitle(chatTitle).chatAnswer(chatAnswer).chatContent(chatContent)
				.build();
	}

	public ChatMessageDto toChatbotListDTO() {
		// TODO Auto-generated method stub
		return ChatMessageDto.builder()
				.chatAnswer(chatAnswer).chatContent(chatContent).chatTitle(chatTitle)
				.build();
	}
}
