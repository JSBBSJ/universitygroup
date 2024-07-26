package com.green.universityGroup.domain.dto;

import java.time.LocalTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseScheduleDTO {
	
	private long scheduleNo;
	private int dayOfWeek;
	private LocalTime startTime;
	private LocalTime endTime;
	private String classRoom;

}
