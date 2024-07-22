package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.green.universityGroup.service.ProfessorService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class ProfessorController {
	
	private final ProfessorService service;
	
	
	@GetMapping("/professor")
	public String list(Model model) {
		service.listProcess(model);	
		return "views/professor/p-main";
	}
	
	  @GetMapping("/professor/edit")
	  public String edit() { 
		  return  "/views/professor/board/edit"; 
		  }
	 
	
	
	@GetMapping("/mail")
	public String mail() {
		return "/views/professor/mail/mail";
	}
	
	@GetMapping("/professor/mail-view")
	public String mail_view() {
		return "/views/professor/mail/mail-view";
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
