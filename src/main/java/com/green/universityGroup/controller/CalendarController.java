package com.green.universityGroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.universityGroup.domain.dto.CalendarDTO;
import com.green.universityGroup.service.CalendarService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class CalendarController {
	 
	    private final CalendarService calendarService;

	    @GetMapping("/view")
	    public String showCalendarView(Model model) {
	    	calendarService.getAllCalendars(model);
	        return "views/commons/calendar";  // 경로이동
	    }
	

	    // 일정 등록
	    @PostMapping("/create")
	    public String createCalendar(@RequestBody CalendarDTO calendarDTO) {
	        calendarService.createCalendar(calendarDTO);
	        return "redirect:/calendar";
	    }

	   
}
