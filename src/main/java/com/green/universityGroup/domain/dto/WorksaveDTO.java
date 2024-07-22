package com.green.universityGroup.domain.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.green.universityGroup.domain.entity.WorkflowSaveEntity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WorksaveDTO {

	private long id;
	private String htmlContent;

	// private String title; //이거는 무슨 타입인지(회의록 또는 휴가신청)
	private String sub; // 제목(신청내)
	private String dra; // 기안자
	private String appr; // 결제자
	private String depart; // 학과명
	private String content; // 내용
	private LocalDateTime worktime; // 기안일

	@JsonCreator
	public WorksaveDTO(@JsonProperty("id") long id, 
			@JsonProperty("htmlContent") String htmlContent,
			@JsonProperty("sub") String sub, 
			@JsonProperty("dra") String dra, 
			@JsonProperty("appr") String appr,
			@JsonProperty("depart") String depart, 
			@JsonProperty("content") String content,
			@JsonProperty("worktime") LocalDateTime worktime) {
		this.id = id;
		this.htmlContent = htmlContent;
		this.sub = sub;
		this.dra = dra;
		this.appr = appr;
		this.depart = depart;
		this.content = content;
		this.worktime = worktime;
	}

	// 저장해주는 편의메서드
	public WorkflowSaveEntity toEntity() {

		return WorkflowSaveEntity.builder().
				id(id).htmlContent(htmlContent)
				.sub(sub)
				.dra(dra)
				.appr(appr)
				.depart(depart)
				.content(content).worktime(worktime).build();
	}
}