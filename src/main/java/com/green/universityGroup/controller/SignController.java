package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignController {
	@GetMapping("/signin")
	public String getMethodName() {
		return "views/commons/signin";
	}

}
