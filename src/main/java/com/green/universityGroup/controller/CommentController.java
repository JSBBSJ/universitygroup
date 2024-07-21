package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.universityGroup.domain.dto.CommentDTO;
import com.green.universityGroup.service.CommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommentController {
	
	private final CommentService service;
	
	

	@PostMapping("add")
	public String commentsave(CommentDTO dto) {
		service.commentsaveProcess(dto);		
		 return "redirect:/professor/view/" + dto.getBoard_no();
	}
	
}
