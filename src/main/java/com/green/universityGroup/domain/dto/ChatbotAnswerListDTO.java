package com.green.universityGroup.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@Getter
public class ChatbotAnswerListDTO {
    private Long id;
    private String category;
    private String text;
    private String answer;
    private Long parentId;
    private Long key;
    private String content;
    private String name;
}