package com.green.universityGroup.service;

import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.CalendarDTO;

public interface CalendarService {

	void getAllCalendars(Model model);

	void createCalendar(CalendarDTO calendarDTO);

}
