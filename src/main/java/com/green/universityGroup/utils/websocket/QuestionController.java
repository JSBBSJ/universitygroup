package com.green.universityGroup.utils.websocket;

import com.green.universityGroup.domain.dto.QuestionDTO;
import com.green.universityGroup.domain.entity.QuestionEntity;
import com.green.universityGroup.service.QuestionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionService service;

	@MessageMapping("/category")
	@SendTo("/topic/category")
	public List<QuestionEntity> getQuestionsByCategory(String category) {
		return service.getQuestionsByCategory(category);
	}

	@MessageMapping("/question/{id}")
	@SendTo("/topic/question")
	public Optional<QuestionEntity> getQuestionById(Long id) {
		return service.getQuestionById(id);
	}

	@MessageMapping("/children/{parentId}")
	@SendTo("/topic/children")
	public List<QuestionEntity> getChildrenQuestions(Long parentId) {
		return service.getChildrenQuestions(parentId);
	}

	@GetMapping("/categories")
	@ResponseBody
	public List<String> getCategories() {
		return service.getAllCategories();
	}

	@GetMapping("/category/{category}/texts")
	@ResponseBody
	public List<String> getTextsByCategory(@PathVariable("category") String category) {
		return service.getTextsByCategory(category);
	}

	@GetMapping("/question/{text}/answer")
	@ResponseBody
	public String getAnswerByText(@PathVariable("text") String text) {
		return service.getAnswerByText(text);
	}
}
