package com.green.universityGroup.domain.dto;

import com.green.universityGroup.domain.entity.BoardEntity;
import com.green.universityGroup.domain.entity.UserEntity;

import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
public class BoardSaveDTO {
	
	private long board_no;
	private String title;
	private String text;
	private String division;
	private long user_no; 
	private UserEntity user;

	public BoardEntity toEntity() {
	    return BoardEntity.builder()
	            .title(title)
	            .text(text)
	            .division(division)
	            .build();
	}
}
