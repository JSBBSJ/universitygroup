package com.green.universityGroup.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.green.universityGroup.domain.dto.QuestionDTO;
import com.green.universityGroup.domain.entity.QuestionEntity;
import com.green.universityGroup.domain.repository.QuestionEntityRepository;
import com.green.universityGroup.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceProcess implements QuestionService {
    private final QuestionEntityRepository repository;

    @Override
    public List<QuestionEntity> getQuestionsByCategory(String category) {
        return repository.findByCategory(category);
    }

    @Override
    public Optional<QuestionEntity> getQuestionById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<QuestionEntity> getChildrenQuestions(Long parentId) {
        Optional<QuestionEntity> parent = repository.findById(parentId);
        return parent.map(repository::findByParent).orElse(null);
    }
    
    @Override
    public List<String> getAllCategories() {
        return repository.findAll().stream()
            .map(QuestionEntity::getCategory)
            .distinct()
            .collect(Collectors.toList());
    }
    @Override
    public List<String> getTextsByCategory(String category) {
        return repository.findByCategory(category).stream()
            .map(QuestionEntity::getText)
            .collect(Collectors.toList());
    }
    
    @Override
    public String getAnswerByText(String text) {
        Optional<QuestionEntity> question = repository.findByText(text);
        return question.map(QuestionEntity::getAnswer).orElse("Answer not found");
    }
}