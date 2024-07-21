package com.green.universityGroup.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatbotAnswerListDTO {

	private long chatbot_no;
	private String chatTitle;
	private String chatContent;
	private String category;
}
