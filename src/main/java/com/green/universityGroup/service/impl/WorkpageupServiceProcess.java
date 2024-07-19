package com.green.universityGroup.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.universityGroup.domain.entity.WorkpageupEntity;
import com.green.universityGroup.domain.repository.WorkpageupRepository;
import com.green.universityGroup.service.WorkpageupService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WorkpageupServiceProcess implements WorkpageupService{

	private final WorkpageupRepository repository;

	@Override
	public void wpagelistProcess(Model model) {
	
		model.addAttribute("list",repository.findAll().stream() 
							.map(WorkpageupEntity::tolistDto)
							.collect(Collectors.toList())
				
				);
		
	}
	
	
	
}
