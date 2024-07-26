package com.green.universityGroup.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.BoardListDTO;
import com.green.universityGroup.domain.dto.BoardSaveDTO;
import com.green.universityGroup.domain.dto.BoardUpdateDTO;
import com.green.universityGroup.domain.entity.BoardEntity;
import com.green.universityGroup.domain.entity.UserEntity;
import com.green.universityGroup.domain.repository.BoardEntityRepository;
import com.green.universityGroup.domain.repository.UserEntityRepository;
import com.green.universityGroup.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceProcess implements BoardService {

	private final BoardEntityRepository repository;

	private final UserEntityRepository userrepository;

	public void listProcess(int _division, Model model) {
		String division;
		switch (_division) {
		case 1:
			division = "자유게시판";
			break;
		case 2:
			division = "학과공지";
			break;
		default:
			division = "학사공지";
		}

		List<BoardListDTO> resultList = repository.findAllByDivision(division).stream().map(BoardEntity::toListDTO)
				.collect(Collectors.toList());

		model.addAttribute("list", resultList);
	}

	@Override
	public void saveProcess(BoardSaveDTO dto) {

		UserEntity user = userrepository.findById(dto.getUser_no())
				.orElseThrow(() -> new IllegalArgumentException("Invalid user_no: " + dto.getUser_no()));

		repository.save(dto.toSaveEntity(user));
	}

	@Override
	public void detailProcess(long board_no, Model model) {
		model.addAttribute("detail", repository.findById(board_no)
				.map(BoardEntity::toProcessDTO).orElseThrow());

	}

	@Override
	@Transactional
	public void updateProcess(long board_no, BoardUpdateDTO dto, long user_no) {
        BoardEntity board = repository.findById(board_no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board_no: " + board_no));
            
            if (board.getUser().getUserNo() != user_no) {
                throw new IllegalStateException("You don't have permission to update this board");
            }
            
            board.update(dto);
        }

	@Override
	public void editProcess(long board_no, Model model) {
		model.addAttribute("detail", repository.findById(board_no).map(BoardEntity::toProcessDTO).orElseThrow());

	}

	@Override
	public void deleteProcess(long board_no, long user_no) {
        BoardEntity board = repository.findById(board_no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board_no: " + board_no));
            
            if (board.getUser().getUserNo() != user_no) {
                throw new IllegalStateException("You don't have permission to delete this board");
            }
}
}