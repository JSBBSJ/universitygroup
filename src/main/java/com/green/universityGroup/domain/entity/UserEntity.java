package com.green.universityGroup.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) //빌더를 사용하려고 생성자초기화 막아주는것
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가 값 생성 
	private long user_no;
	
	@Column(nullable=false , length = 50)
	private String username;
	
	@Column(columnDefinition = "varchar(100) COLLATE ut8mb4_bin", nullable = false, unique = true)
	private String email;
	
	@Column(columnDefinition = "varchar(255) COLLATE ut8mb4_bin", nullable = false)
	private String password;
	
	//롤 역할_enum 제외
	
	@Column(nullable = false)
	private String profile_image;
	
	
	@Column(nullable = false)
	private String last_login_ip;
	
	//챗봇 번호?? fk 
	
  @ManyToOne
  @JoinColumn(name = "chatbot_no", referencedColumnName = "chatbot_no")
	private ChatbotEntity chatbot;

}