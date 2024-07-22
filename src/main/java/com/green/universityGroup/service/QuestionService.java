package com.green.universityGroup.service;

import java.util.Collection;
import java.util.List;

import com.green.universityGroup.domain.entity.QuestionEntity;

public interface QuestionService {

	List<QuestionEntity> getQuestionsByCategory(String category);
    List<String> getAllCategories();
	String getAnswer(String category, String text);
}
