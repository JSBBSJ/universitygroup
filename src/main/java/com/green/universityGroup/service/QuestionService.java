package com.green.universityGroup.service;

import java.util.List;
import java.util.Optional;

import com.green.universityGroup.domain.dto.QuestionDTO;
import com.green.universityGroup.domain.entity.QuestionEntity;

public interface QuestionService {

	List<QuestionEntity> getQuestionsByCategory(String category);

	Optional<QuestionEntity> getQuestionById(Long id);

	List<QuestionEntity> getChildrenQuestions(Long parentId);

	List<String> getAllCategories();

	List<String> getTextsByCategory(String category);

	String getAnswerByText(String text);
	
}
