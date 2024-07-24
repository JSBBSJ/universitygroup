package com.green.universityGroup.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.universityGroup.domain.entity.CourseEntity;

import com.green.universityGroup.domain.entity.UserEntity;
import com.green.universityGroup.domain.repository.CourseRepository;
import com.green.universityGroup.domain.repository.UserEntityRepository;
import com.green.universityGroup.service.CourseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseServiceProcess implements CourseService {

	private final CourseRepository repository;
	private final UserEntityRepository userRepository;

	public void classList(String username, Model model) {
		UserEntity user = userRepository.findByUsername(username);

		List<CourseEntity> courses = repository.findByProfessorProfessorNo(user.getProfessor().getProfessor_no());
		model.addAttribute("courses", courses);

	}

}
