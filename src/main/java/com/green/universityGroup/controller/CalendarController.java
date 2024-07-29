package com.green.universityGroup.controller;

import com.green.universityGroup.domain.dto.CalendarCreateDTO;
import com.green.universityGroup.domain.dto.CalendarReadDTO;
import com.green.universityGroup.domain.dto.CalendarUpdateDTO;
import com.green.universityGroup.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/calendar")
    public String showCalendarView(Model model) {
        model.addAttribute("calendars", calendarService.getAllCalendars());
        return "views/commons/calendar";
    }

    @PostMapping("/calendar") 	              
    public String createCalendar(@Validated @ModelAttribute CalendarCreateDTO calendarCreateDTO) {
        calendarService.createCalendar(calendarCreateDTO);
        return "redirect:/calendar";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        CalendarReadDTO calendarDTO = calendarService.getCalendarById(id);
        model.addAttribute("calendar", calendarDTO);
        return "views/commons/editCalendar";
    }

    @PostMapping("/{id}/update")
    public String updateCalendar(@PathVariable Long id, @Validated @ModelAttribute CalendarUpdateDTO calendarUpdateDTO) {
        calendarService.updateCalendar(id, calendarUpdateDTO);
        return "redirect:/calendar";
    }

    @PostMapping("/{id}/delete")
    public String deleteCalendar(@PathVariable Long id) {
        calendarService.deleteCalendar(id);
        return "redirect:/calendar";
    }
}
    
    