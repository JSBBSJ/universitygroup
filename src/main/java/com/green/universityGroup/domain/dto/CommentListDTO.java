package com.green.universityGroup.domain.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CommentListDTO {
	
	private long comment_no;
	private String comment_user;
	private String text;
	private LocalDateTime createdAt;
	private long board_no;
}
