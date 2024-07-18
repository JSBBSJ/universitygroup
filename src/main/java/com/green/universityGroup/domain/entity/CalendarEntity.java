package com.green.universityGroup.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Entity
@Table(name="calendar")
public class CalendarEntity {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long calendar_no;

	    @Column(nullable = false)
	    private String title;

	    @Column(columnDefinition = "timestamp")
	    private LocalDateTime start_date;

	    @Column(columnDefinition = "timestamp")
	    private LocalDateTime end_date;

	    @Column(columnDefinition = "TEXT")
	    private String description;

	    @Column(nullable = false)
	    private Boolean is_all_day;
	    
	    @OneToOne
		@JoinColumn(name = "user_no", referencedColumnName = "user_no")
	    private UserEntity user;

	    
	}