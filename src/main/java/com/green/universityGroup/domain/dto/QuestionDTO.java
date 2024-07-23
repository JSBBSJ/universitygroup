package com.green.universityGroup.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionDTO {
    private Long id;
    private String category;
    private String text;
    private String answer;
    private Long parentId;
    private List<QuestionDTO> children;
}