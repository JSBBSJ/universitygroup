package com.green.universityGroup.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.ProfessorClassListDTO;
import com.green.universityGroup.domain.entity.CourseEntity;
import com.green.universityGroup.domain.repository.CourseRepository;
import com.green.universityGroup.service.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceProcess implements CourseService {

	private final CourseRepository repository;

	@Override
	public void getCourseList(String username, Model model) {
	        model.addAttribute("courses", repository.findAll()
	        		.stream().map(CourseEntity::toListDTO)
	        		.collect(Collectors.toList()));

	}


}
