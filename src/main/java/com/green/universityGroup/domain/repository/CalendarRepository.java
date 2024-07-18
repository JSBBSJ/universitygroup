package com.green.universityGroup.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.universityGroup.domain.entity.CalendarEntity;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

}
