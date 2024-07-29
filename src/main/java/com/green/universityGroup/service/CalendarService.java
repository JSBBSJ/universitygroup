package com.green.universityGroup.service;

import java.util.List;

import com.green.universityGroup.domain.dto.CalendarCreateDTO;
import com.green.universityGroup.domain.dto.CalendarReadDTO;
import com.green.universityGroup.domain.dto.CalendarUpdateDTO;

public interface CalendarService {

    List<CalendarReadDTO> getAllCalendars(); 

    void createCalendar(CalendarCreateDTO calendarDTO); // 생성

    CalendarReadDTO getCalendarById(Long id); //조회

    void updateCalendar(Long id, CalendarUpdateDTO calendarDTO); //수정

    void deleteCalendar(Long id); //삭제
}
