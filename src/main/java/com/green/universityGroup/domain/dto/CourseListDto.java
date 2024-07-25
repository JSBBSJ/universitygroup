package com.green.universityGroup.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CourseListDto {

	private long course_no; // 과목번호
	private String course_name; // 과목명
	private String class_room; //강의실
	private long credit; //학점
	private String professor_name; //교수명? or username?
	private String department_name; // 개설학과

	
}	
