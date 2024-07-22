package com.green.universityGroup.domain.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.universityGroup.domain.dto.ChatbotAnswerListDTO;
import com.green.universityGroup.domain.entity.ChatbotEntity;

public interface ChatbotEntityRepository extends JpaRepository<ChatbotEntity, Long> {

	List<ChatbotEntity> findByCategory(String category);


}
