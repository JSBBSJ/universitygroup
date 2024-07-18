package com.green.universityGroup.domain.entity;


import java.time.LocalDateTime;

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
	private long schedule_no;
	
	@Column(nullable = false)
	private int day_of_week;
	
	@Column(columnDefinition = "timestamp")
	private LocalDateTime start_time;
	
	@Column(columnDefinition = "timestamp")
	private LocalDateTime end_time;
	
	@Column(nullable = false, length = 50)
	private String class_room;
	
	@ManyToOne
	@JoinColumn(name = "course_no", referencedColumnName = "course_no")
	private CourseEntity course;
}
