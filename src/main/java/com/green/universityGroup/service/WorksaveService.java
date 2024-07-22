package com.green.universityGroup.service;

import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.WorksaveDTO;

public interface WorksaveService {

	void saveprocess(WorksaveDTO dto);

	void listprocess(Model model);

}