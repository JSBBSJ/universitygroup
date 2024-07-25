package com.green.universityGroup.domain.repository;

import com.green.universityGroup.domain.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {
}
