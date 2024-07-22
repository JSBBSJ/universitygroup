package com.green.universityGroup.domain.dto;

import com.green.universityGroup.domain.entity.BoardEntity;
import com.green.universityGroup.domain.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class BoardSaveDTO {
	
	private long board_no;
	private String title;
	private String text;
	private String division;
	private long user_no; 


	public BoardEntity toSaveEntity(UserEntity user) {
	    return BoardEntity.builder()
	            .title(title)
	            .text(text)
	            .division(division)
	            .user(user)
	            .build();
	}
}
