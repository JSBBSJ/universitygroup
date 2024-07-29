package com.green.universityGroup.domain.dto;

import com.green.universityGroup.domain.entity.BoardEntity;
import com.green.universityGroup.domain.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveDTO {
	
	private long board_no;
	private String title;
	private String text;
	private String division;
	private long user_no; 
	private String username;

	public BoardSaveDTO(String title, String text, String division, long user_no ,String username) {

        this.title = title;
        this.text = text;
        this.division = division;
        this.user_no = user_no;
        this.username = username;
    }
	
	  public BoardEntity toSaveEntity(UserEntity user) { 
	  return BoardEntity.builder() 
	  .title(title) 
	  .text(text) 
	  .division(division)
	  .user(user) 
	  .build(); 
	  }
	 
}
