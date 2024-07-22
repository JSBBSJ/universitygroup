package com.green.universityGroup.domain.entity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import com.green.universityGroup.domain.dto.WorksaveDTO;

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
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) //빌더를 사용하려고 생성자초기화 막아주는것
@NoArgsConstructor
@Entity
@Table(name = "worksave")
public class WorkflowSaveEntity {
	
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id; //pk 아이디

	   @Column( length = 5000)
	   private String htmlContent;  //html 태그 저장
	   
		/*
		 * @Column(nullable = false) private String title; //제목
		 */	   
	   
	   @Column(nullable = false)
	   private String sub; //제목(신청서 내)
		
	   @Column(nullable = false)
	   private String dra; //기안자
		
	   @Column(nullable = false)
	   private String appr; //결제자
	   
	   private String depart; //학과명
	   
	   @CreationTimestamp
       @Column(columnDefinition = "timestamp")
	   private LocalDateTime worktime;  //기안일
	   
	   @Column(length = 1000)
	   private String content;

	   //저장된 값을 조회해주는 편의 메서드 
	public WorksaveDTO tolistDto() {
		
		return WorksaveDTO.builder()
				.id(id)
				.htmlContent(htmlContent)
				.sub(sub)
				.dra(dra).appr(appr)
				.depart(depart)
				.worktime(worktime)
				.build();
	}

}