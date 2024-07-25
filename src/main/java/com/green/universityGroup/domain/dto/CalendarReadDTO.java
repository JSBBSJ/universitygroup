package com.green.universityGroup.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarReadDTO {
	private Long id;
    private String title;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String description;
    private Boolean is_all_day;
    private Long user_no;
}
