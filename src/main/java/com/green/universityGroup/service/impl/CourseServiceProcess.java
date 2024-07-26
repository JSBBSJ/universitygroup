package com.green.universityGroup.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.green.universityGroup.domain.repository.StudentRepository;
import com.green.universityGroup.security.CustomUserDetails;
import com.green.universityGroup.service.CourseService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceProcess implements CourseService {

	private final CourseRepository courseRepository; // 과목정보를 조회할 레포지토리
	//private final EnrollmentRepository enrollmentRepository; // 수강 신청 정보를 저장할 레포지토리
	private final StudentRepository studentRepository;

	@Override
	public void getCourseList(String username, Model model) {
		model.addAttribute("courses", courseRepository.findByProfessor_User_Username(username).stream()
				.map(CourseEntity::toListDTO).collect(Collectors.toList()));
	}

	@Override
	public void getCourseList(CustomUserDetails user, Model model) {
		List<CourseListDto> courses = courseRepository.findAll().stream()
				.map(CourseEntity::toCourseListDto)// CourseEntity -> CourseListDto
				.collect(Collectors.toList())
				; // 모든 과목목록 조회
		model.addAttribute("courses", courses);// 모델에 과목목록 추가
		
		//로그인한 학생의 정보
		
		//*
		StudentEntity studentEntity= studentRepository.findById(user.getStudentDTO().getStudent_no()).orElseThrow();
		List<CourseListDto> myAddedCourses=studentEntity.getEnrollment().stream()
												.map(enrollment->enrollment.getCourse().toCourseListDto())
												.collect(Collectors.toList());
		model.addAttribute("myAddedCourses", myAddedCourses);//내가등록한수강정보
		//*/
		
	}

	@Override
	public void getCourseList(CourseListDto dto, Model model) {
		//List<CourseEntity> courses = courseRepository.findAll(); // 모든 과목목록 조회
		//model.addAttribute("courses", courses);// 모델에 과목목록 추가
	}
	
	//private final StudentRepository studentRepository;
	//private final CourseRepository courseRepository;
	
	@Transactional
	@Override
	public void enrollCourse(Long studentNo, Long courseNo) {
		System.out.println("stdNo:"+studentNo);
		System.out.println("courseNo:"+courseNo);
		CourseEntity courseEntity=courseRepository.findById(courseNo).orElseThrow();
		//CourseEntity courseEntity=CourseEntity.builder().courseNo(courseNo).build();
		//학생 엔티티 생성 & 과목 엔티티 생성 방법 2가지 있음
		//1. repository로 db에서 조회해서 생성
		studentRepository.findById(studentNo).orElseThrow().addEnrollment(EnrollmentEntity.builder().course(courseEntity).build());
		//2. Builder 객체로 pk만 세팅해서 생성
		//StudentEntity studentEntity=StudentEntity.builder().studentNo(studentNo).build();
		
		//***이미등록한 과목이 있는지 체크가 안되고 있음 : 학생-수강과목 테이블의 관계를 수정 학생(부모)-수강과목(자식)
		// 수강 신청을 처리하는 로직
	
	}

	@Override
	public void getStudentEnrollments(Long student_no, Model model) {
		// 학생의 수강 목록을 조회하는 로직
		//List<EnrollmentEntity> enrollments = enrollmentRepository.findByStudentStudentNo(student_no);
		//model.addAttribute("enrollments", enrollments); // 모델에 수강 목록 추가

	}


	
}
