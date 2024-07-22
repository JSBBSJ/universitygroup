package com.green.universityGroup.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
    public List<String> getAllCategories() {
        return repository.findAll().stream()
                .map(QuestionEntity::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public String getAnswer(String category, String text) {
        return repository.findByCategory(category).stream()
                .filter(q -> q.getText().equals(text))
                .findFirst()
                .map(QuestionEntity::getAnswer)
                .orElse("Sorry, I don't understand the question.");
    }
}
