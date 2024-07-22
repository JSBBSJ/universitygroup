package com.green.universityGroup.service.impl;

import java.util.List;
import java.util.Optional;

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
	                .start_date(calendarDTO.getStart_date())
	                .end_date(calendarDTO.getEnd_date())
	                .description(calendarDTO.getDescription())
	                .is_all_day(calendarDTO.getIs_all_day())
	                .build();
	        calendarRepository.save(calendarEntity);
	    }

	    @Override
	    public CalendarDTO getCalendarById(Long id) {
	        Optional<CalendarEntity> optionalCalendar = calendarRepository.findById(id);
	        if (optionalCalendar.isPresent()) {
	            CalendarEntity calendarEntity = optionalCalendar.get();
	            return CalendarDTO.builder()
	                    .calendar_no(calendarEntity.getCalendar_no())
	                    .title(calendarEntity.getTitle())
	                    .start_date(calendarEntity.getStart_date())
	                    .end_date(calendarEntity.getEnd_date())
	                    .description(calendarEntity.getDescription())
	                    .is_all_day(calendarEntity.getIs_all_day())
	                    .build();
	        } else {
	            throw new RuntimeException("일정을 찾을 수 없습니다.");
	        }
	    }

	    @Override
	    public void updateCalendar(Long id, CalendarDTO calendarDTO) {
	       calendarRepository.findById(id).orElseThrow().update(calendarDTO);
	        
	    }

	    @Override
	    public void deleteCalendar(Long id) {
	        if (calendarRepository.existsById(id)) {
	            calendarRepository.deleteById(id);
	        } else {
	            throw new RuntimeException("일정을 찾을 수 없습니다.");
	        }
	    }
	}
