package com.green.universityGroup.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.universityGroup.domain.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Long>{

	List<CourseEntity> findByProfessorProfessorNo(long professor_no);

}
