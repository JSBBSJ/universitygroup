package com.green.universityGroup.service.impl;

import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import com.green.universityGroup.domain.entity.ProfessorEntity;
import com.green.universityGroup.domain.repository.ProfessorEntityRepository;
import com.green.universityGroup.service.ProfessorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProfessorServiceProcess implements ProfessorService{
	
	private final ProfessorEntityRepository repository;
	
	
	public void listProcess(Model model) {	
		model.addAttribute("list", repository.findAll().stream()
		.map(ProfessorEntity::toListDTO)
		.collect(Collectors.toList()));
	}


	@Override
    public String getLoggedInProfessorUsername() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

}
