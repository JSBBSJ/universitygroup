package com.green.universityGroup.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CalendarCreateDTO {

	private String title;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String description;
    private Boolean is_all_day = false;
    private Long user_no;
}
