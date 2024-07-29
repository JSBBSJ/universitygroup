package com.green.universityGroup.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.universityGroup.domain.entity.BoardEntity;
import com.green.universityGroup.domain.entity.CommentEntity;
import java.util.List;


public interface CommentEntityRepository extends JpaRepository<CommentEntity, Long> {
	 List<CommentEntity> findByBoard(BoardEntity board);

}
