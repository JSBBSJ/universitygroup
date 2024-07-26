package com.green.universityGroup.domain.entity;


import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.DynamicUpdate;

import com.green.universityGroup.domain.dto.CourseScheduleDTO;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DynamicUpdate
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "courseSchdule")
@Entity
public class CourseScheduleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long scheduleNo;
	
	@Column(nullable = false)
	private int dayOfWeek;
	
	@Column(columnDefinition = "timestamp")
	private LocalDateTime startTime;
	
	@Column(columnDefinition = "timestamp")
	private LocalDateTime endTime;
	
	@Column(nullable = false, length = 50)
	private String classRoom;
	
	@ManyToOne
	@JoinColumn(name = "course_no")
	private CourseEntity course;
	
	public CourseScheduleDTO toCourseScheduleDTO() {
		LocalTime startTime;
		LocalTime endTime;
		if (this.startTime == null)
			startTime = LocalTime.now();
		else
			startTime = this.startTime.toLocalTime();
		
		if (this.endTime == null)
			endTime = LocalTime.now();
		else
			endTime = this.endTime.toLocalTime();
		
		return CourseScheduleDTO.builder()
				.scheduleNo(scheduleNo)
				.dayOfWeek(dayOfWeek)
				.startTime(startTime)
				.endTime(endTime)
				.classRoom(classRoom)
				.build();
	}
}
