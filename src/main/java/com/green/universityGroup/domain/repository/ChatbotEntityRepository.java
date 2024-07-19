package com.green.universityGroup.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.universityGroup.domain.entity.ChatbotEntity;

public interface ChatbotEntityRepository extends JpaRepository<ChatbotEntity, Long> {

	ChatbotEntity findBychatContent(String content);


}
