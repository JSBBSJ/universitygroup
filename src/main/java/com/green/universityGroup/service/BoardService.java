package com.green.universityGroup.service;

import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.BoardSaveDTO;
import com.green.universityGroup.domain.entity.UserEntity;

public interface BoardService {

	void listProcess(int division, Model model);

	void saveProcess(BoardSaveDTO dto);
	
}
