package com.green.universityGroup.service;

import java.util.List;

import com.green.universityGroup.domain.entity.ChatbotEntity;

public interface ChatbotEntityService {


	String getAnswerByQuestionId(Long questionId);

	List<ChatbotEntity> getAllChatbotEntries();


}
