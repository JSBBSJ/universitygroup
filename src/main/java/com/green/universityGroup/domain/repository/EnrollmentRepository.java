package com.green.universityGroup.domain.repository;

import com.green.universityGroup.domain.entity.EnrollmentEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 이 인터페이스가 스프링 데이터 JPA 리포지토리임을 나타냅니다.
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {

    // 특정 학생 번호로 수강 신청 목록을 조회하는 메서드입니다.
    List<EnrollmentEntity> findByStudentStudentNo(Long student_no);
}
