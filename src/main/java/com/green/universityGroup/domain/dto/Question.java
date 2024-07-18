package com.green.universityGroup.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {
	
	private long key;
	private String title; // 질문 제목
    private String content; // 질문 내용
    private String answer; // 질문에 대한 답변
	
	
	public String getAnswer() {
		
		return answer;
	}
}