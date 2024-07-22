package com.green.universityGroup.service.impl;

import com.green.universityGroup.domain.dto.ChatbotAnswerListDTO;
import com.green.universityGroup.domain.entity.ChatbotEntity;
import com.green.universityGroup.domain.repository.ChatbotEntityRepository;

import com.green.universityGroup.service.ChatbotEntityService;


import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChatbotEntityServiceProcess implements ChatbotEntityService {

    private final ChatbotEntityRepository repository;

    @Override
    public List<ChatbotAnswerListDTO> getCategoryListProcess(String category) {
        List<ChatbotEntity> chatbotEntities = repository.findByCategory(category); // ChatbotEntity 목록 조회

        List<ChatbotAnswerListDTO> categoryList = chatbotEntities.stream()  // ChatbotEntity 스트림 생성
                .map(ChatbotEntity::toListDTO)  // ChatbotEntity를 ChatbotAnswerListDTO로 변환
                .collect(Collectors.toList()); 

        return categoryList;
    }


    @Override
    public ChatbotAnswerListDTO getChatbotContentListProcess(Long chatbot_no) {
        return repository.findById(chatbot_no)
                .map(ChatbotEntity::toListDTO) // map 메서드를 사용하여 ChatbotAnswerListDTO로 변환
                .orElse(null);
    }


    public List<ChatbotAnswerListDTO> getAllChatbotEntries() {
        List<ChatbotEntity> chatbotEntities = repository.findAll();
        List<ChatbotAnswerListDTO> chatbotDTOs = chatbotEntities.stream()
                .map(ChatbotEntity::toListDTO)
                .collect(Collectors.toList());
        
        return chatbotDTOs;
    }




}