package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//수강신청 컨트롤러
@Controller
public class EnrolmentController {

	//최조 전자결재 화면에서 작성하기 눌렀을때 페이지 이동하는 컨트롤러
		@GetMapping("/enrolment-main")//이게 수정되면 자바 스크립트 towiriting page 경로도 수정 필요
		public String write() {
			return "/views/student/enrolment/enrolment-main";
		}

}
