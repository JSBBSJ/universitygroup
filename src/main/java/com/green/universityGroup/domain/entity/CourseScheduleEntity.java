package com.green.universityGroup.domain.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.DynamicUpdate;

import com.green.universityGroup.domain.dto.CourseScheduleDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DynamicUpdate // 엔티티의 변경된 부분만 업데이트 하도록 설정합니다.
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 매개변수로 받는 생성자를 생성합니다. 접근 레벨은 PRIVATE입니다.
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성합니다.
@Builder // 빌더 패턴을 사용하여 객체를 생성할 수 있도록 합니다.
@Setter // 모든 필드에 대한 setter 메서드를 자동으로 생성합니다.
@Getter // 모든 필드에 대한 getter 메서드를 자동으로 생성합니다.
@Table(name = "courseSchdule") // 데이터베이스 테이블과 매핑합니다.
@Entity // JPA 엔티티임을 나타냅니다.
public class CourseScheduleEntity {

    @Id // 기본 키를 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임합니다.
    private long scheduleNo; // 강의 스케줄 고유 번호
    
    @Column(nullable = false) // Not null 제약 조건을 설정합니다.
    private int dayOfWeek; // 강의 요일 (1: 월요일, 2: 화요일, ..., 7: 일요일)
    
    @Column(columnDefinition = "timestamp") // 컬럼 정의를 타임스탬프로 설정합니다.
    private LocalDateTime startTime; // 강의 시작 시간
    
    @Column(columnDefinition = "timestamp") // 컬럼 정의를 타임스탬프로 설정합니다.
    private LocalDateTime endTime; // 강의 종료 시간
    
    @Column(nullable = false, length = 50) // Not null 제약 조건과 최대 길이 50을 설정합니다.
    private String classRoom; // 강의실
    
    @ManyToOne // 다대일 관계를 설정합니다.
    @JoinColumn(name = "course_no") // 외래 키를 설정합니다.
    private CourseEntity course; // 강의 과목
    
    // CourseScheduleDTO 객체로 변환하는 메서드입니다.
    public CourseScheduleDTO toCourseScheduleDTO() {
        LocalTime startTime;
        LocalTime endTime;
        
        // 시작 시간이 null인 경우 현재 시간으로 설정합니다.
        if (this.startTime == null)
            startTime = LocalTime.now();
        else
            startTime = this.startTime.toLocalTime();
        
        // 종료 시간이 null인 경우 현재 시간으로 설정합니다.
        if (this.endTime == null)
            endTime = LocalTime.now();
        else
            endTime = this.endTime.toLocalTime();
        
        // CourseScheduleDTO 객체를 빌더 패턴으로 생성하여 반환합니다.
        return CourseScheduleDTO.builder()
                .scheduleNo(scheduleNo) // 스케줄 번호 설정
                .dayOfWeek(dayOfWeek) // 요일 설정
                .startTime(startTime) // 시작 시간 설정
                .endTime(endTime) // 종료 시간 설정
                .classRoom(classRoom) // 강의실 설정
                .build();
    }
}
