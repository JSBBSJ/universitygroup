package com.green.universityGroup.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Getter
@Table(name = "enrollment")
@Entity
public class EnrollmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long enrollment_no;
	
	@ManyToOne
	@JoinColumn(name = "student_no", referencedColumnName = "student_no")
	private StudentEntity student;
	
	@ManyToOne
	@JoinColumn(name = "course_no", referencedColumnName = "course_no")
	private CourseEntity course;
}
