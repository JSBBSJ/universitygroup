package com.green.universityGroup.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProfessorClassDTO {

	private Long courseNo;
	private String courseName;
	private String userName;
}
