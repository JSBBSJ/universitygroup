package com.green.universityGroup.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.universityGroup.domain.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{

}
