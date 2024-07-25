package com.green.universityGroup.controller;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.universityGroup.domain.dto.WorksaveDTO;
import com.green.universityGroup.service.WorksaveService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WorksaveController {
	
	
	private final WorksaveService sevice;
	
	//@PostMapping(value = "/saveDoc")
	@PostMapping("/professor/saveDoc")
	@ResponseBody
	public String saveDoc(@RequestBody WorksaveDTO dto) {
	
	    sevice.saveprocess(dto);
	    //return "redirect:/views/professor/workflow/work-list";
	    return "저장";
	}
	
	//저장된 값 save페이지에서 가져오기
	@GetMapping("/professor/work-save")
	public String list(Model model) {
		sevice.listprocess(model);
		return "views/professor/workflow/work-save";
	}
	
	
	//상세페이지로 이동하는 페이지(비동기)
	@GetMapping("/professor/detailpage")
	public String detailpage() {
				
		return "views/professor/workflow/worklistpageup"; //여기도 패키지에 맞게 수정필요 최초 "/"이거였음
	}
	
	
	//그 상세페이지에 값 뿌려주기
	@GetMapping("/worklistpageup")
    public String detaillist(Model model) {
        sevice.listprocess(model);
        return "views/professor/workflow/worklistpageup";
    }
	
	
}