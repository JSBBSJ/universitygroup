package com.green.universityGroup.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class LmsController {
	
	
	@GetMapping("/student/lms")
	public String list() {
		return "views/lms/list";
	}
	
	
	@GetMapping("/mypage")
	public String professorMypage() {
		return "views/commons/mypage";
	}
	
	
	@GetMapping("/course-detail")
	public String courseList() {
		return "views/lms/course-detail";
	}
	
}
