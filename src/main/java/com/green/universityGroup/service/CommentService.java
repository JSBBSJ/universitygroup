package com.green.universityGroup.service;

import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.CommentDTO;

public interface CommentService {

	void commentsaveProcess(CommentDTO dto);

	void commentlistProcess(long board_no, Model model);

	

}
