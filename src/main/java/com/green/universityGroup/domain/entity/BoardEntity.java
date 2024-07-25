package com.green.universityGroup.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.green.universityGroup.domain.dto.BoardDetailDTO;
import com.green.universityGroup.domain.dto.BoardListDTO;
import com.green.universityGroup.domain.dto.BoardSaveDTO;
import com.green.universityGroup.domain.dto.BoardUpdateDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "board")
public class BoardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long board_no;

	@Column(nullable = false, length = 100)
	private String division;

	@Column(nullable = false)
	private String title;

	@Column(columnDefinition = "text", nullable = false)
	private String text;

	@CreationTimestamp
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(columnDefinition = "timestamp")
	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name = "user_no", referencedColumnName = "user_no")
	private UserEntity user;

	@OneToMany(mappedBy = "board")
	private List<CommentEntity> comments;
	
	
	
	
	public BoardListDTO toListDTO() {
		return BoardListDTO.builder()
				.board_no(board_no)
				.title(title)
				.createdAt(createdAt)
				.username(user.getUsername())
				 .user_no(user.getUser_no()) 
				.division(division)
				.build();
	}
	
	public BoardDetailDTO toProcessDTO() {
		return BoardDetailDTO.builder()
				.board_no(board_no)
				.division(division)
				.title(title)
				.text(text)
				.createdAt(createdAt)
				.username(user.getUsername())
				.build();
	}

	public BoardEntity update(BoardUpdateDTO dto) {
		this.title=dto.getTitle();
		this.text=dto.getText();
		return this;
		
	}
		
	}
	
	