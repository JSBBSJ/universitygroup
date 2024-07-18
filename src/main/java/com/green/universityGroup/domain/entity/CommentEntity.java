package com.green.universityGroup.domain.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

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
	private long comment_no;

	@Column(nullable = false)
	private String comment_user;

	@Column(columnDefinition = "text", nullable = false)
	private String text;

	@CreationTimestamp
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "board_no", referencedColumnName = "board_no")
	private BoardEntity board;

}
