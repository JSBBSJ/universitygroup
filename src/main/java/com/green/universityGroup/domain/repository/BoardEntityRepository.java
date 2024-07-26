package com.green.universityGroup.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.green.universityGroup.domain.entity.BoardEntity;
import com.green.universityGroup.domain.entity.UserEntity;

public interface BoardEntityRepository extends JpaRepository<BoardEntity, Long> {
	
	 List<BoardEntity> findAllByDivision(String division); 
	 
	 List<BoardEntity> findByUser(UserEntity user);
	 
	 Page<BoardEntity> findAllByDivision(String division, Pageable pageable);
}
