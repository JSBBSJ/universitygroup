package com.green.universityGroup.service.impl;

import com.green.universityGroup.domain.entity.ChatbotEntity;
import com.green.universityGroup.domain.repository.ChatbotEntityRepository;

import com.green.universityGroup.service.ChatbotEntityService;


import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChatbotEntityServiceProcess implements ChatbotEntityService {

    private final ChatbotEntityRepository repository;


    @Override
    public String getAnswerByQuestionId(Long id) {
        ChatbotEntity entity = repository.findById(id).orElse(null);
        return entity != null ? entity.getChatContent() : "질문에 대한 답변이 없습니다.";
    }


    @Override
    public List<ChatbotEntity> getAllChatbotEntries() {
        return repository.findAll();
    }


}