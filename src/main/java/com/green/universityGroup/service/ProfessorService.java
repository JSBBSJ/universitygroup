package com.green.universityGroup.service;

import org.springframework.ui.Model;

public interface ProfessorService {

	void listProcess(Model model);

	String getLoggedInProfessorUsername();


}
