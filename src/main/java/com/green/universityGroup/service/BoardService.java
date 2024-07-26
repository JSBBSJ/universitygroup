package com.green.universityGroup.service;

import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.BoardSaveDTO;
import com.green.universityGroup.domain.dto.BoardUpdateDTO;
import com.green.universityGroup.domain.entity.UserEntity;

public interface BoardService {

	void listProcess(int division, Model model);

	void saveProcess(BoardSaveDTO dto);

	void detailProcess(long board_no, Model model);

	void editProcess(long board_no, Model model);

    void updateProcess(long board_no, BoardUpdateDTO dto, long user_no);
    
    void deleteProcess(long board_no, long user_no);
}
