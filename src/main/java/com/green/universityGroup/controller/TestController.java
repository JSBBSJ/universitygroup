package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TestController {

	@GetMapping("/test")
	public String list() {
		return "/views/test/test";
	}
	
	
	@GetMapping("/test")
	public String write() {
		return "/views/test/test";
	}
}
