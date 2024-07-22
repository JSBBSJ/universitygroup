package com.green.universityGroup.domain.entity;

import java.util.Set;
import java.util.function.Function;

import org.hibernate.annotations.DynamicUpdate;

import com.green.universityGroup.domain.dto.StudentlistDTO;

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


//빌더 뺌 (부모에서 빌더 있으면 자식에서는 없어도 된다고 함)
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) //빌더를 사용하려고 생성자초기화 막아주는것
@NoArgsConstructor
@Entity
@Table(name = "student")
public class StudentEntity {
	
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
	
	@OneToMany(mappedBy = "enrollment_no", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<EnrollmentEntity> enrollment;

	
	//dto에 있는정보를 entity에 다시 반환
	public StudentlistDTO tolistDto() {
		
		
		return StudentlistDTO.builder()
				.student_number(student_number)
				.username(user.getUsername())
				.department_name(department.getDepartment_name())
				.build();
	}
	
	
}