package com.green.universityGroup.domain.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardDetailDTO {
	
	private long board_no;
	private String division;
	private String title;
	private String text;
	private String username;
	private LocalDateTime createdAt;
}
