package com.green.universityGroup.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.universityGroup.domain.entity.QuestionEntity;

public interface QuestionEntityRepository extends JpaRepository<QuestionEntity, Long>{

	List<QuestionEntity> findByCategory(String category);

}
