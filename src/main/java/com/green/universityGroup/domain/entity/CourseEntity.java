package com.green.universityGroup.domain.entity;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.catalina.User;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;

import com.green.universityGroup.domain.dto.CourseListDto;
import com.green.universityGroup.domain.dto.ProfessorClassListDTO;

import jakarta.persistence.CascadeType;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="course")
@Entity
public class CourseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long courseNo;
	
	@Column(nullable = false, unique = true)
	private String courseName;
	
	@Column (nullable = false)
	private long credit;
	
	@JoinColumn(name = "professor_no")
	@ManyToOne
	private ProfessorEntity professor;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<EnrollmentEntity> enrollment;
	
	@OneToMany(mappedBy = "course")
	private List<CourseScheduleEntity> courseSchedule;
	
	public CourseListDto toCourseListDto() {
		return CourseListDto.builder()
				.courseNo(courseNo)
				.courseName(courseName)
				.credit(credit)
				.professorName(professor.getUser().getUsername())
				.departmentName(professor.getDepartment().getDepartmentName())
				.courseSchedules(courseSchedule.stream()
						.map(CourseScheduleEntity::toCourseScheduleDTO)
						.collect(Collectors.toList()))
				.build();
	}
	

	public ProfessorClassListDTO toListDTO() {
        return ProfessorClassListDTO.builder()
                .course_name(courseName)
                .build();
	}
}
