package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

		@GetMapping("/student")
		public String main() {
			return "/views/student/s-main";
		}
		
}
