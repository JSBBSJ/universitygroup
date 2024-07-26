package com.green.universityGroup.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.BoardSaveDTO;
import com.green.universityGroup.domain.dto.BoardUpdateDTO;
import com.green.universityGroup.domain.entity.Division;
import com.green.universityGroup.security.RaraUniversityUserDetails;
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
	public String list(@PathVariable("division") int division, 
            @RequestParam(name = "page", defaultValue = "1") int page, 
            Model model) {
			service.listProcess(division, model, page);
		return "views/professor/board/board";
	}

	@GetMapping("/board/write")
	public String write() {
		return "views/professor/board/write";
	}

	@PostMapping("/board/write")
	public String save(BoardSaveDTO dto) {
		service.saveProcess(dto);
		String redirectPath;
		switch (dto.getDivision()) {
		case "자유게시판":
			redirectPath = "/board/1";
			break;
		case "학과공지":
			redirectPath = "/board/2";
			break;
		case "학사공지":
			redirectPath = "/board/3";
			break;
		default:
			redirectPath = "/board/1";
			break;
		}

		return "redirect:" + redirectPath;
	}

	@GetMapping("/board/view/{board_no}")
	public String detail(@PathVariable("board_no") long board_no, Model model) {
		service.detailProcess(board_no, model);
		return "/views/professor/board/view";
	}

	@GetMapping("/board/edit/{board_no}")
	public String edit(@PathVariable("board_no") long board_no, Model model) {
		service.editProcess(board_no, model);
		return "/views/professor/board/edit";
	}

	@PutMapping("/board/edit/{board_no}")
	public String update(@PathVariable("board_no") long board_no, BoardUpdateDTO dto,
			@AuthenticationPrincipal RaraUniversityUserDetails userDetails) {
		service.updateProcess(board_no, dto, userDetails.getUser_no());
		return "redirect:/board/view/{board_no}";
	}

	@DeleteMapping("/board/view/{board_no}")
	public String delete(@PathVariable("board_no") long board_no,

	        @AuthenticationPrincipal RaraUniversityUserDetails userDetails) {
	    service.deleteProcess(board_no, userDetails.getUser_no());
	    return "redirect:/board/1?page=1";


	}
}
