package com.green.universityGroup.domain.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.universityGroup.domain.dto.ProfessorClassListDTO;
import com.green.universityGroup.domain.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Long>{

	List<CourseEntity> findByProfessor_User_Username(String username);


}



