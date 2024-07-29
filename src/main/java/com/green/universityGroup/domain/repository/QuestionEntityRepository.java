package com.green.universityGroup.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.universityGroup.domain.dto.QuestionDTO;
import com.green.universityGroup.domain.entity.QuestionEntity;


public interface QuestionEntityRepository extends JpaRepository<QuestionEntity, Long> {

	List<QuestionEntity> findByCategory(String category);
	 List<QuestionEntity> findByParent(QuestionEntity parent);
	Optional<QuestionEntity> findByText(String text);
    
}