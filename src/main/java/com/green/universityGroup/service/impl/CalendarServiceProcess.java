package com.green.universityGroup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.CalendarDTO;
import com.green.universityGroup.domain.entity.CalendarEntity;
import com.green.universityGroup.domain.repository.CalendarRepository;
import com.green.universityGroup.service.CalendarService;

@Service
public class CalendarServiceProcess implements CalendarService{

	  @Autowired
	    private CalendarRepository calendarRepository;

	    @Override
	    public void getAllCalendars(Model model) {
	        List<CalendarEntity> calendars = calendarRepository.findAll();
	        model.addAttribute("calendars", calendars);
	    }

	    @Override
	    public void createCalendar(CalendarDTO calendarDTO) {
	        CalendarEntity calendarEntity = CalendarEntity.builder()
	                .title(calendarDTO.getTitle())
	                .start_date(calendarDTO.getEnd_date())
	                .end_date(calendarDTO.getEnd_date())
	                .description(calendarDTO.getDeseription())
	                .is_all_day(calendarDTO.getIs_all_day())
	                .build();
	        calendarRepository.save(calendarEntity);
	    }
	}
