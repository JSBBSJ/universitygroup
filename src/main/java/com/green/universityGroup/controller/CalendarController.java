	package com.green.universityGroup.controller;
	
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
		    	calendarService.getAllCalendars(model);
		        return "views/commons/calendar";  // 경로이동
		    }
		
	
		    // 일정 등록
		    @PostMapping("/calendar")
		    public String createCalendar(@RequestBody CalendarDTO calendarDTO) {
		        calendarService.createCalendar(calendarDTO);
		        return "redirect:/calendar";
		    }
		    
		    // 일정 수정 폼
		    @GetMapping("/calendar/{id}/edit")
		    public String showEditForm(@PathVariable Long id, Model model) {
		        CalendarDTO calendarDTO = calendarService.getCalendarById(id);
		        model.addAttribute("calendar", calendarDTO);
		        return "views/commons/editCalendar"; // 수정 폼 뷰 경로
		    }
	
		    // 일정 수정
		    @PostMapping("/calendar/{id}")
		    public String updateCalendar(@PathVariable Long id, @RequestBody CalendarDTO calendarDTO) {
		        calendarService.updateCalendar(id, calendarDTO);
		        return "redirect:/calendar";
		    }
	
		    // 일정 삭제
		    @DeleteMapping("/calendar/{id}")
		    public String deleteCalendar(@PathVariable Long id) {
		        calendarService.deleteCalendar(id);
		        return "redirect:/calendar";
		    }
		}
	
