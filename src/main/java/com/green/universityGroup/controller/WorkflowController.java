package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WorkflowController {

	@GetMapping("/workflow")
	public String list() {
		return "views/professor/workflow";
	}
	
}
