package com.green.universityGroup.service;

import java.util.List;

import com.green.universityGroup.domain.entity.ChatbotEntity;

public interface ChatbotEntityService {

	List<ChatbotEntity> getAllChatbotEntries();

	String getAnswerByQuestionId(Long questionId);


}
