package com.green.universityGroup.utils.websocket;

import org.springframework.web.bind.annotation.RestController;

import com.green.universityGroup.domain.entity.QuestionEntity;
import com.green.universityGroup.service.QuestionService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionService service;

	@GetMapping("/questions")
	public List<QuestionEntity> getQuestions(@RequestParam String category) {
		System.out.println("Received request for category: " + category); // 디버깅
		return service.getQuestionsByCategory(category);
	}

}
