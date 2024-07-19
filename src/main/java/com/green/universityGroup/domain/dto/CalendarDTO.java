package com.green.universityGroup.domain.dto;

import java.time.LocalDateTime;

import com.green.universityGroup.domain.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDTO {

	private Long calendar_no;
	private String title;
	private LocalDateTime start_date;
	private LocalDateTime end_date;
	private String description;
	private Boolean is_all_day;
	private UserEntity user;
	 
	
}
