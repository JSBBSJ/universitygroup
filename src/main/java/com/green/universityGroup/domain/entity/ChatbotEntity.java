package com.green.universityGroup.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Getter
@Table(name = "chatbot")
@Entity
public class ChatbotEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long chatbot_no;
	
	@Column(columnDefinition = "varchar(100)", nullable = false)
	private String chat_title;
	
	@Column(columnDefinition = "text", nullable = false)
	private String chat_content;


}
