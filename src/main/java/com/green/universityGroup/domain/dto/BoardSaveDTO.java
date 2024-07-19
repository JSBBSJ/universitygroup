package com.green.universityGroup.domain.dto;

import com.green.universityGroup.domain.entity.BoardEntity;

import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
public class BoardSaveDTO {
	
	private String title;
	private String text;
	private String division;

	public BoardEntity toEntity() {
		return BoardEntity.builder()
				.title(title)
				.text(text)
				.division(division)
				.build();
	}



}
