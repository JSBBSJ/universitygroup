package com.green.universityGroup.domain.dto;

import java.util.List;
import java.util.Objects;

import com.green.universityGroup.domain.entity.CourseEntity;

import lombok.Builder;
import lombok.Getter;


//CourseListDto 클래스는 강좌 목록 정보를 담는 데이터 전송 객체입니다.
@Getter
@Builder
public class CourseListDto {

 // 과목 고유 번호 (Primary Key)
 private long courseNo; 
 
 // 과목명
 private String courseName; 
 
 // 학점
 private long credit; 
 
 // 교수명 (교수의 이름을 저장, username을 사용하려면 필드명 변경 필요)
 private String professorName; 
 
 // 개설 학과명
 private String departmentName; 
 
 // 강의 일정 목록
 private List<CourseScheduleDTO> courseSchedules; 
 
//equals 메서드 재정의
 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     CourseListDto that = (CourseListDto) o;
     return courseNo == that.courseNo;
 }

 // hashCode 메서드 재정의
 @Override
 public int hashCode() {
     return Objects.hash(courseNo);
 }
}