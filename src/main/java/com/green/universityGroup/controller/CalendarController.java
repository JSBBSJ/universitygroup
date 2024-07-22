	package com.green.universityGroup.controller;
	
	import java.util.Optional;

import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import com.green.universityGroup.domain.dto.CalendarDTO;
	import com.green.universityGroup.service.CalendarService;
	
	import lombok.RequiredArgsConstructor;
	
	
	@Controller
	@RequiredArgsConstructor
	public class CalendarController {
	    
	    private final CalendarService calendarService;

	    @GetMapping("/calendar")
	    public String showCalendarView(Model model) {
	        model.addAttribute("calendars", calendarService.getAllCalendars());
	        return "views/commons/calendar";  // 경로이동
	    }

	    @PostMapping("/calendar")
	    public String createCalendar(@RequestBody CalendarDTO calendarDTO) {
	        calendarService.createCalendar(calendarDTO);
	        return "redirect:/calendar";
	    }

	    @GetMapping("/calendar/{id}/edit")
	    public String showEditForm(@PathVariable Long id, Model model) {
	        Optional<CalendarDTO> calendarDTO = calendarService.getCalendarById(id);
	        if (calendarDTO.isPresent()) {
	            model.addAttribute("calendar", calendarDTO.get());
	            return "views/commons/editCalendar"; // 수정 폼 뷰 경로
	        } else {
	            return "redirect:/calendar"; // 404 페이지 혹은 에러 페이지로 리다이렉트
	        }
	    }

	    @PostMapping("/calendar/{id}")
	    public String updateCalendar(@PathVariable Long id, @RequestBody CalendarDTO calendarDTO) {
	        Optional<CalendarDTO> updatedCalendar = calendarService.updateCalendar(id, calendarDTO);
	        if (updatedCalendar.isPresent()) {
	            return "redirect:/calendar";
	        } else {
	            return "redirect:/calendar"; // 404 페이지 혹은 에러 페이지로 리다이렉트
	        }
	    }

	    @DeleteMapping("/calendar/{id}")
	    public String deleteCalendar(@PathVariable Long id) {
	        calendarService.deleteCalendar(id);
	        return "redirect:/calendar";
	    }
	}
