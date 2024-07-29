package com.green.universityGroup.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.WorksaveDTO;
import com.green.universityGroup.domain.entity.WorkflowSaveEntity;
import com.green.universityGroup.domain.repository.WorksaveRepository;
import com.green.universityGroup.service.WorksaveService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WorksaveServiceProcess implements WorksaveService{

	private final WorksaveRepository repository;
	
	@Override  //저장해주는 메서드
	public void saveprocess(WorksaveDTO dto) {
		repository.save(dto.toEntity());
		
	}

	@Override  //저장한걸 불러오는 메서드
	public void listprocess(Model model) {
		
		model.addAttribute("list",repository.findAll().stream()
							.map(WorkflowSaveEntity::tolistDto)
							.collect(Collectors.toList())
				);
	}

}