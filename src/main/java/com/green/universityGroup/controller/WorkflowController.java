package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.universityGroup.service.WorkflowService;


@Controller
public class WorkflowController {
	
	//private final WorkflowService service;

	@GetMapping("/workflow")
	public String list() {
		return "/views/professor/workflow/workflow";
	}
	
	//최조 전자결재 화면에서 작성하기 눌렀을때 페이지 이동하는 컨트롤러
		@GetMapping("/work-list")//이게 수정되면 자바 스크립트 towiriting page 경로도 수정 필요
		public String write() {
			return "/views/professor/workflow/work-list"; //여기 경로 수정필요(패키지에 맞게)
		}
		
		
		//휴가신청서 등 자바스크립트에서 찾아오기위해 만든 컨트롤러(휴가신청서 이름: vacationapplicationform)
		//@ResponseBody//데이터로 가라는 뜻
		@GetMapping("/findDoc")
		public String findDoc(@RequestParam("data") String data) {
			
			return "/views/professor/workflow/"+data; //여기도 패키지에 맞게 수정필요 최초 "/"이거였음
		}
		
		
		/*//workpageup 컨트롤러가 따로 있어서 주석 처리
		 * //결제선 지정 버튼 클릭시 새 팝업창 뜨게 하는 컨트롤러
		 * 
		 * @GetMapping("/w-pageup") public String wPageup() {
		 * 
		 * return "/views/professor/workflow/w-pageup"; //이것도 경로가 달라지면 수정 필요//이건 양식폼
		 * 버튼태그 }
		 */
		
		/* work save 컨트롤러 따로 있어 주석처리
		 * @GetMapping("/work-save") public String report() {
		 * 
		 * return "/views/professor/workflow/work-save"; //이것도 경로가 달라지면 수정 필요//이건 양식폼
		 * 버튼태그 }
		 */
	
		
	
}