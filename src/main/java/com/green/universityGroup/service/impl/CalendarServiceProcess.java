package com.green.universityGroup.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.green.universityGroup.domain.dto.CalendarCreateDTO;
import com.green.universityGroup.domain.dto.CalendarUpdateDTO;
import com.green.universityGroup.domain.dto.CalendarReadDTO;
import com.green.universityGroup.domain.entity.CalendarEntity;
import com.green.universityGroup.domain.entity.UserEntity;
import com.green.universityGroup.domain.repository.CalendarRepository;
import com.green.universityGroup.domain.repository.UserRepository;
import com.green.universityGroup.service.CalendarService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarServiceProcess implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    @Override
    public List<CalendarReadDTO> getAllCalendars() {
        List<CalendarEntity> calendarEntities = calendarRepository.findAll();
        return calendarEntities.stream()
                .map(this::convertToReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createCalendar(CalendarCreateDTO calendarDTO) {
        if (calendarDTO.getUser_no() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User number must not be null");
        }

        UserEntity user = userRepository.findById(calendarDTO.getUser_no())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        
        CalendarEntity calendarEntity = convertToEntity(calendarDTO);
        calendarEntity.setUser(user);
        calendarRepository.save(calendarEntity);
    }
    @Override
    public CalendarReadDTO getCalendarById(Long id) {
        CalendarEntity calendarEntity = calendarRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Calendar not found"));
        return convertToReadDTO(calendarEntity);
    }

    @Override
    @Transactional
    public void updateCalendar(Long id, CalendarUpdateDTO calendarDTO) {
        CalendarEntity calendarEntity = calendarRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Calendar not found"));
        
        UserEntity user = userRepository.findById(calendarDTO.getUser_no())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        calendarEntity.update(calendarDTO, user);
        calendarRepository.save(calendarEntity);
    }

    @Override
    @Transactional
    public void deleteCalendar(Long id) {
        CalendarEntity calendarEntity = calendarRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Calendar not found"));
        calendarRepository.delete(calendarEntity);
    }

    private CalendarEntity convertToEntity(CalendarCreateDTO calendarDTO) {
        return CalendarEntity.builder()
                .title(calendarDTO.getTitle())
                .start_date(calendarDTO.getStart_date())
                .end_date(calendarDTO.getEnd_date())
                .description(calendarDTO.getDescription())
                .is_all_day(calendarDTO.getIs_all_day())
                .build();
    }

    private CalendarReadDTO convertToReadDTO(CalendarEntity calendarEntity) {
        return CalendarReadDTO.builder()
                .id(calendarEntity.getCalendar_no())
                .title(calendarEntity.getTitle())
                .start_date(calendarEntity.getStart_date())
                .end_date(calendarEntity.getEnd_date())
                .description(calendarEntity.getDescription())
                .is_all_day(calendarEntity.getIs_all_day())
                .user_no(calendarEntity.getUser() != null ? calendarEntity.getUser().getUser_no() : null)
                .build();
    }
}
