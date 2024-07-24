package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.universityGroup.service.CourseService;
import com.green.universityGroup.service.ProfessorService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class ProfessorLmsController {
	
	private final CourseService service;
	private final ProfessorService professorService;
	
	@GetMapping("/professor/lms")
	public String courseList(Model model) {
		String username = professorService.getLoggedInProfessorUsername();
		service.getCourseList(username, model);
		return "views/lms/professor-list";
	}

	
	

}
