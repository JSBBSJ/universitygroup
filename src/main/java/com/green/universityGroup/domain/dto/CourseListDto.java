package com.green.universityGroup.domain.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CourseListDto {

	private long courseNo; // 과목번호
	private String courseName; // 과목명
	
	private long credit; //학점
	private String professorName; //교수명? or username?
	private String departmentName; // 개설학과

	private List<CourseScheduleDTO> courseSchedules; //강의실
	
}	
