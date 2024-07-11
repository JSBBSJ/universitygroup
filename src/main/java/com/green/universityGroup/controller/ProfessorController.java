package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@Controller
public class ProfessorController {

	@GetMapping("/professor")
	public String main() {
		return "/views/professor/p-main";
	}
	
	@GetMapping("/board")
	public String board() {
		return "/views/professor/board";
	}
	
	
	@GetMapping("/professor/new")
	public String write() {
		return "/views/professor/wirte";
	}
	
	@GetMapping("/professor/{no}")
	public String detail(@PathVariable("no") long no) {
		return "/views/professor/detail";
	}
	
	@PutMapping("/professor/{no}")
	public String update(@PathVariable("no") long no) {
		return "redirect:/professor";
	}
}
