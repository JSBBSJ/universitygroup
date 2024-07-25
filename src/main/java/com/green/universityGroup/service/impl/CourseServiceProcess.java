package com.green.universityGroup.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.ProfessorClassListDTO;
import com.green.universityGroup.domain.entity.CourseEntity;
import com.green.universityGroup.domain.repository.CourseRepository;
import com.green.universityGroup.domain.dto.CourseListDto;
import com.green.universityGroup.domain.entity.CourseEntity;
import com.green.universityGroup.domain.entity.EnrollmentEntity;
import com.green.universityGroup.domain.entity.StudentEntity;
import com.green.universityGroup.domain.repository.CourseRepository;
import com.green.universityGroup.domain.repository.EnrollmentRepository;
import com.green.universityGroup.service.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceProcess implements CourseService {

	private final CourseRepository courseRepository; // 과목정보를 조회할 레포지토리
	private final EnrollmentRepository enrollmentRepository; // 수강 신청 정보를 저장할 레포지토리

	@Override
	public void getCourseList(String username, Model model) {
		model.addAttribute("courses", courseRepository.findByProfessor_User_Username(username).stream()
				.map(CourseEntity::toListDTO).collect(Collectors.toList()));
	}


	@Override
	public void getCourseList(CourseListDto dto, Model model) {
		List<CourseEntity> courses = courseRepository.findAll(); // 모든 과목목록 조회
		model.addAttribute("courses", courses);// 모델에 과목목록 추가
	}

	@Override
	public void enrollCourse(Long student_no, Long course_no) {
		// 수강 신청을 처리하는 로직
		EnrollmentEntity enrollment = EnrollmentEntity.builder()
				/*
				 * .student(new StudentEntity(student_no)) // 학생 엔티티 생성
				 * .course(new CourseEntity(course_no)) // 과목 엔티티 생성
				 */ .build();
		enrollmentRepository.save(enrollment); // 수강 신청 정보 저장
	}

	@Override
	public void getStudentEnrollments(Long student_no, Model model) {
		// 학생의 수강 목록을 조회하는 로직
		List<EnrollmentEntity> enrollments = enrollmentRepository.findByStudentStudentNo(student_no);
		model.addAttribute("enrollments", enrollments); // 모델에 수강 목록 추가

	}
}
