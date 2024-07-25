package com.green.universityGroup.service.impl;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import com.green.universityGroup.domain.dto.CommentDTO;
import com.green.universityGroup.domain.dto.CommentListDTO;
import com.green.universityGroup.domain.entity.BoardEntity;
import com.green.universityGroup.domain.entity.CommentEntity;

import com.green.universityGroup.domain.repository.BoardEntityRepository;
import com.green.universityGroup.domain.repository.CommentEntityRepository;
import com.green.universityGroup.service.CommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentServiceProcess implements CommentService {
	
	private final CommentEntityRepository repository;
	
	private final BoardEntityRepository boardRepository; 
	
	@Override
	public void commentsaveProcess(CommentDTO dto) {
	   
		BoardEntity board = boardRepository.findById(dto.getBoard_no())
                .orElseThrow(() -> new IllegalArgumentException("Invalid board_no: " + dto.getBoard_no()));


        repository.save(dto.toCommentEntity(board));
    }
	

	@Override
	public void commentlistProcess(long board_no, Model model) {
		
		
		  List<CommentListDTO> comments = repository.findAll() 
		  .stream()
		  .map(CommentEntity::toListDTO)
		  .collect(Collectors.toList());
		  model.addAttribute("comments", comments);
		 
	}
}
