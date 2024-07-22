package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.BoardSaveDTO;
import com.green.universityGroup.domain.dto.BoardUpdateDTO;
import com.green.universityGroup.domain.entity.Division;
import com.green.universityGroup.service.BoardService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




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
	
	
	@GetMapping("/professor/view/{board_no}")
	public String detail(@PathVariable("board_no") long board_no, Model model) {
		service.detailProcess(board_no,model);
		return "/views/professor/board/view";
	}
	
	@GetMapping("/professor/edit/{board_no}")
	public String edit(@PathVariable("board_no") long board_no, Model model) {
		service.editProcess(board_no,model);
		return "/views/professor/board/edit";
	}
	
	@PutMapping("/professor/edit/{board_no}")
	public String update(@PathVariable("board_no") long board_no, BoardUpdateDTO dto) {
		service.updateProcess(board_no,dto);		
		return "redirect:/professor/view/{board_no}";
	}
	
	@DeleteMapping("/professor/view/{board_no}")
	public String delete(@PathVariable("board_no") long board_no) {
		service.deleteProcess(board_no);
		return "redirect:/board/1";
	}
	
}
