package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.BoardSaveDTO;
import com.green.universityGroup.domain.entity.Division;
import com.green.universityGroup.service.BoardService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;





@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService service;
	 	
	
	@GetMapping("/board/{division}")
	public String list(@PathVariable("division") int division ,Model model) {
		service.listProcess(division,model);
		return "views/professor/board/board";
	}
	
	
	@GetMapping("/board/write")
	public String write() {
		return "views/professor/board/write";
	}
	
	
	@PostMapping("/board/write")
	public String save(BoardSaveDTO dto) {
		service.saveProcess(dto);
		return "redirect:/board/1";
	}
	
	
	
	
	
}
