package com.green.universityGroup.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfessorListDTO {
	
	private long professorNo;
	private long professor_number;
	private String username;
	private String department_name;
	
}
