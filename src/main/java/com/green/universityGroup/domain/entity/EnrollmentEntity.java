package com.green.universityGroup.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	private long enrollment_no; // 수강신청 번호
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_no", referencedColumnName = "student_no")
	private StudentEntity student; // 학생정보
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_no", referencedColumnName = "course_no")
	private CourseEntity course; // 과목정보

}
