package com.green.universityGroup.domain.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import com.green.universityGroup.domain.dto.BoardListDTO;
import com.green.universityGroup.domain.dto.CommentDTO;
import com.green.universityGroup.domain.dto.CommentListDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Entity
@Table(name = "comment")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long commentNo;

	@Column(nullable = false)
	private String comment_user;

	@Column(columnDefinition = "text", nullable = false)
	private String text;

	@CreationTimestamp
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "boardNo")
	private BoardEntity board;
	
	
	public CommentListDTO toCommentListDTO() {
		return CommentListDTO.builder()
				.text(text)
				.comment_no(commentNo)
				.comment_user(comment_user)
				.createdAt(createdAt)
				.board_no(board.getBoardNo())
				.build();
	}
	
}
