package com.green.universityGroup.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.ChatMessageDto;
import com.green.universityGroup.domain.entity.ChatbotEntity;
import com.green.universityGroup.domain.repository.ChatbotEntityRepository;
import com.green.universityGroup.service.ChatbotService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatbotServiceProcess implements ChatbotService {

	private final ChatbotEntityRepository repository;

	
	@Override
    public String getAnswerForQuestion(String question) {
        ChatbotEntity chatbot = repository.findBychatContent(question);
        return chatbot != null ? chatbot.getChatAnswer() : "답변을 찾을 수 없습니다.";
    }


	@Override
	public String getAllQuestions(Model model) {
		model.addAttribute("list", repository.findAll().stream()
				.map(ChatbotEntity::toChatbotListDTO)
				.collect(Collectors.toList()));
		return null;
	}


	
}
