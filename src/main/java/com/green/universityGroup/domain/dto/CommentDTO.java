package com.green.universityGroup.domain.dto;

import java.time.LocalDateTime;

import com.green.universityGroup.domain.entity.BoardEntity;
import com.green.universityGroup.domain.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor 
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class CommentDTO {
	
	
	private long comment_no;
	private String comment_user;
	private String text;
	private LocalDateTime createdAt;
	private long board_no;

	public CommentEntity toCommentEntity(BoardEntity board) {
		return CommentEntity.builder()
				.comment_no(comment_no)
				.comment_user(comment_user)
				.createdAt(createdAt)
				.text(text)
				.board(board)
				.build();
	}

}