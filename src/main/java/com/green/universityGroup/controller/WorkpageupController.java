package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.universityGroup.service.StudentService;
import com.green.universityGroup.service.WorkpageupService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WorkpageupController {
	
	
	private final WorkpageupService service;

	
	//주소록에 저장된 db 값을 조회하기 위한 컨트롤러
	@GetMapping("/w-pageup")
	public String list(Model model) {
		
		service.wpagelistProcess(model);
		
		return "views/professor/workflow/w-pageup";
	}
	
	
}