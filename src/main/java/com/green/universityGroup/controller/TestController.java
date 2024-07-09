package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class TestController {

	@GetMapping("/test")
	public String list() {
		return "views/test/test";
	}
	
	@GetMapping("/test/new")
	public String write() {
		return "views/test/write";
	}
	
	@GetMapping("/test/{no}")
	public String detail(@PathVariable("no") long no) {
		return "views/test/detail";
	}
	
	@PutMapping("/test/{no}")
	public String update(@PathVariable("no") long no) {
		return "redirect:/test";
	}
	
}
