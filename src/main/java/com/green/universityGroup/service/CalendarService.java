package com.green.universityGroup.service;

import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.CalendarDTO;

public interface CalendarService {

	void getAllCalendars(Model model);//반환

	void createCalendar(CalendarDTO calendarDTO);//생성
	
    CalendarDTO getCalendarById(Long id); // 불러오기
    
    void updateCalendar(Long id, CalendarDTO calendarDTO); //수정

    void deleteCalendar(Long id);//삭제



}


