package com.green.universityGroup.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.green.universityGroup.domain.dto.ProfessorListDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)  //빌더를 사용하려고 생성자초기화 막아주는것
@NoArgsConstructor
@Entity
@Table(name = "professor")
public class ProfessorEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가 값 생성 
	private long professorNo;	
	
	private long professorNumber;
	
	@OneToOne(mappedBy = "professor")  //일대일
	private UserEntity user;
	
	@ManyToOne //다대일
    @JoinColumn(name = "departmentNo")
	private DepartmentEntity department;

	public ProfessorListDTO toListDTO() {
		return ProfessorListDTO.builder()
				.professorNo(professorNo)
				.professor_number(professorNumber)
				.username(user.getUsername())
				.department_name(department.getDepartmentName())
				.build();
	
	}
	
}
	