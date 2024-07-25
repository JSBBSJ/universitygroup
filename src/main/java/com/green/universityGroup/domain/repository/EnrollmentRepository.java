package com.green.universityGroup.domain.repository;

import com.green.universityGroup.domain.entity.EnrollmentEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {

	List<EnrollmentEntity> findByStudentStudentNo(Long student_no);


}