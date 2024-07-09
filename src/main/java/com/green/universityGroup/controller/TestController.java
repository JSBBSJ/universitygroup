package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/test")
	public String list() {
		return "/views/test/test";
	}
	
	@GetMapping("/test/new")
	public String write() {
		return "/views/test/write";
	}
	
	@GetMapping("/test/{no}")
	public String detail() {
		return "/views/test/detail";
	}
	
}
