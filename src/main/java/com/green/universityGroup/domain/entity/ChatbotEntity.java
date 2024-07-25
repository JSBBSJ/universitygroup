/*
 * package com.green.universityGroup.domain.entity;
 * 
 * import java.util.ArrayList; import java.util.List; import
 * java.util.function.Function;
 * 
 * import org.hibernate.annotations.DynamicUpdate;
 * 
 * import com.green.universityGroup.domain.dto.ChatbotAnswerListDTO;
 * 
 * import jakarta.persistence.CascadeType; import jakarta.persistence.Column;
 * import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
 * import jakarta.persistence.GenerationType; import jakarta.persistence.Id;
 * import jakarta.persistence.JoinColumn; import jakarta.persistence.ManyToOne;
 * import jakarta.persistence.OneToMany; import jakarta.persistence.Table;
 * import lombok.AccessLevel; import lombok.AllArgsConstructor; import
 * lombok.Builder; import lombok.Getter; import lombok.NoArgsConstructor;
 * 
 * @DynamicUpdate
 * 
 * @AllArgsConstructor(access = AccessLevel.PRIVATE)
 * 
 * @NoArgsConstructor
 * 
 * @Builder
 * 
 * @Getter
 * 
 * @Table(name = "chatbot")
 * 
 * @Entity public class ChatbotEntity {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private long chatbot_no;
 * 
 * @Column(columnDefinition = "varchar(100)", nullable = false) private String
 * chatTitle;
 * 
 * @Column(columnDefinition = "text", nullable = false) private String
 * chatContent;
 * 
 * @Column(columnDefinition = "varchar(100)", nullable = false) private String
 * category;
 * 
 * public ChatbotAnswerListDTO toListDTO() { return ChatbotAnswerListDTO
 * .builder() .chatbot_no(chatbot_no) // chatbot_no 필드 사용 .chatTitle(chatTitle)
 * .chatContent(chatContent) .category(category) .build(); } }
 */