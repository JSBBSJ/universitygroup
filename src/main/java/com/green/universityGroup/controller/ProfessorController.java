package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class ProfessorController {

	@GetMapping("/professor")
	public String main() {
		return "/views/professor/p-main";
	}
	
	@GetMapping("/professor/new")
	public String write() {
		return "/views/professor/wirte";
	}
	
	@GetMapping("/professor/{no}")
	public String detail(@PathVariable("no") long no) {
		return "/views/professor/detail";
	}
}
