package com.green.universityGroup.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.universityGroup.domain.entity.StudentEntity;
import com.green.universityGroup.domain.repository.StudentRepository;
import com.green.universityGroup.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceProcess implements StudentService {

	private final StudentRepository repository;
	
	@Override //db에 이미 저장된 값 불러오는 메서드 
	public void SlistProcess(Model model) {
		
		model.addAttribute("list",repository.findAll().stream()
							.map(StudentEntity::tolistDto)
							.collect(Collectors.toList())
				
				);
		
	}

}
