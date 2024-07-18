package com.green.universityGroup.service;


import org.springframework.ui.Model;


public interface ChatbotService {
    
    String getAllQuestions(Model model);

	String getAnswerForQuestion(String question);


}
