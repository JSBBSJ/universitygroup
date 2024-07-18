package com.green.universityGroup.domain.dto;

import lombok.Data;

@Data
public class Question {
	
	private long key;//nanotime을 적용:질문자를 구분하기위한 값
	private String content;//질문내용
}