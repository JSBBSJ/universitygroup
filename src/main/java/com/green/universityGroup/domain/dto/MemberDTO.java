package com.green.universityGroup.domain.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.green.universityGroup.domain.entity.WorkflowSaveEntity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class MemberDTO {
	
	
	private long member_no;
	private String member_name;
	private String member_sub;
	private String member_roll;
	private String member_num;

}