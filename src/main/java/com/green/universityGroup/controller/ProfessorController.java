package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ProfessorController {

	@GetMapping("/professor")
	public String main() {
		return "/views/professor/p-main";
	}
	
}
