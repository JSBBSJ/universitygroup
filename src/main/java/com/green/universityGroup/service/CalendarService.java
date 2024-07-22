package com.green.universityGroup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.CalendarDTO;

public interface CalendarService {

    List<CalendarDTO> getAllCalendars();

    CalendarDTO createCalendar(CalendarDTO calendarDTO);

    CalendarDTO getCalendarById(Long id);

    void updateCalendar(Long id, CalendarDTO calendarDTO);

    void deleteCalendar(Long id);

	void getAllCalendars(Model model);
}
