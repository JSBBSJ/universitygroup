package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.green.universityGroup.service.StudentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudentController {
	
	private final StudentService service;

	/*
	 * @GetMapping("/student") public String main() { return
	 * "/views/student/s-main"; }
	 */
		
		//db 이미 저장된 값 저장된 내용 조회화면 불러오기
		@GetMapping("/student")
		public String check(Model model) {
			
			service.SlistProcess(model);
			return "views/student/s-main";
		}
		
}
