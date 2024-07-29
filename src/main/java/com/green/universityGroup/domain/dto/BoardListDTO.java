package com.green.universityGroup.domain.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardListDTO {
	
	private long board_no;
	private String title;
	private LocalDateTime createdAt;
	private String username;
	private String division;
	private long user_no;
}
