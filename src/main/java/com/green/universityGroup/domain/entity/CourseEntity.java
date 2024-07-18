package com.green.universityGroup.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="course")
@Entity
public class CourseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long course_no;
	
	@Column(nullable = false, unique = true)
	private String course_name;
	
	@Column (nullable = false)
	private long credit;
	
	@JoinColumn(name = "professor_no" ,referencedColumnName = "professor_no")
	@ManyToOne
	private ProfessorEntity professor;
	
	@OneToMany(mappedBy = "course_no", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<EnrollmentEntity> enrollment;
}
