package com.green.universityGroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.green.universityGroup.domain.dto.UserUpdateDTO;
import com.green.universityGroup.service.UserService;
import com.green.universityGroup.service.impl.UserServiceProcess;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService service;
	
	@PutMapping("updateProfile/{user_no}")
	public String putMethodName(@PathVariable long user_no, UserUpdateDTO dto) {
		service.updateProfile(user_no, dto);
		return "views/commons/mypage";
	}
	
	
	
}
