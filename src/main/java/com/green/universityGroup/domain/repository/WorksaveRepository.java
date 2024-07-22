package com.green.universityGroup.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.universityGroup.domain.entity.WorkflowSaveEntity;

public interface WorksaveRepository extends JpaRepository<WorkflowSaveEntity , Long> {
	
	

}
