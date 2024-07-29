package com.green.universityGroup.domain.dto;

import java.time.LocalTime;

import lombok.Builder;
import lombok.Getter;

//CourseScheduleDTO 클래스는 수업 시간표 정보를 담는 데이터 전송 객체입니다.
@Getter
@Builder
public class CourseScheduleDTO {

 // 스케줄 고유 번호 (Primary Key)
 private long scheduleNo;

 // 수업 요일 
 private int dayOfWeek;

 // 수업 시작 시간
 private LocalTime startTime;

 // 수업 종료 시간
 private LocalTime endTime;

 // 수업이 진행되는 강의실
 private String classRoom;
}