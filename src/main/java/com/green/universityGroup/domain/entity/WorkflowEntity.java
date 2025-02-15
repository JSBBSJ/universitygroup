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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Entity
@Table(name = "workflow")
public class WorkflowEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long workflowNo;

	
	@ManyToOne
	@JoinColumn(name = "professorNo")
	private ProfessorEntity professor;
	
	
	@OneToOne
    @JoinColumn(name = "workpageupNo")
	private WorkpageupEntity workpageup;
	

}