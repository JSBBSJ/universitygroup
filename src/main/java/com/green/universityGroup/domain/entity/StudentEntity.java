package com.green.universityGroup.domain.entity;

import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) //빌더를 사용하려고 생성자초기화 막아주는것
@NoArgsConstructor
@Entity
@Table(name = "student")
public class StudentEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가 값 생성 
	private long student_no;
	
	@Column(nullable = false, unique = true)
	private long student_number;
	
	@OneToOne
   @JoinColumn(name = "user_no", referencedColumnName = "user_no")
	private UserEntity user;
	
	@ManyToOne
   @JoinColumn(name = "department_no", referencedColumnName = "department_no")
	private DepartmentEntity department;
	
	@OneToMany(mappedBy = "student_no", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<EnrollmentEntity> enrollment;
	
	
}