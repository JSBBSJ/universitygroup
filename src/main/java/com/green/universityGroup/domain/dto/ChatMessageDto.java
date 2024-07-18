package com.green.universityGroup.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMessageDto {

	private String key;
	private String chatTitle;
	private String chatContent;
	private String chatAnswer;
}
