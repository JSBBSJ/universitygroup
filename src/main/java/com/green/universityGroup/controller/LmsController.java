package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
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
