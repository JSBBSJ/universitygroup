package com.green.universityGroup.service;

import java.util.List;

import com.green.universityGroup.domain.dto.ChatbotAnswerListDTO;

public interface ChatbotEntityService {

	List<ChatbotAnswerListDTO> getCategoryListProcess(String category);

	ChatbotAnswerListDTO getChatbotContentListProcess(Long chatbot_no);

	List<ChatbotAnswerListDTO> getAllChatbotEntries();


}
