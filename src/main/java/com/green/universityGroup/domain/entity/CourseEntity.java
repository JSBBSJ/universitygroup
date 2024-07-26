package com.green.universityGroup.domain.entity;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.catalina.User;
import org.hibernate.annotations.DynamicUpdate;

import com.green.universityGroup.domain.dto.CourseListDto;
import com.green.universityGroup.domain.dto.ProfessorClassListDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate // 엔티티가 변경된 부분만 업데이트 하도록 설정합니다.
@Builder // 빌더 패턴을 사용하여 객체를 생성할 수 있도록 합니다.
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성합니다.
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 파라미터로 받는 생성자를 생성합니다. 접근 레벨은 PRIVATE입니다.
@Getter // 모든 필드에 대한 getter 메서드를 자동으로 생성합니다.
@Table(name = "course") // 데이터베이스 테이블과 매핑합니다.
@Entity // JPA 엔티티임을 나타냅니다.
public class CourseEntity {

    @Id // 기본 키를 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임합니다.
    private long courseNo; // 과목 고유 번호
    
    @Column(nullable = false, unique = true) // Not null 제약 조건과 유니크 제약 조건을 설정합니다.
    private String courseName; // 과목명
    
    @Column(nullable = false) // Not null 제약 조건을 설정합니다.
    private long credit; // 학점
    
    @JoinColumn(name = "professor_no") // 외래 키를 설정합니다.
    @ManyToOne // 다대일 관계를 설정합니다.
    private ProfessorEntity professor; // 담당 교수
    
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true) // 일대다 관계를 설정합니다. CASCADE 옵션과 고아 객체 제거를 설정합니다.
    private Set<EnrollmentEntity> enrollment; // 수강 신청 목록
    
    @OneToMany(mappedBy = "course") // 일대다 관계를 설정합니다.
    private List<CourseScheduleEntity> courseSchedule; // 강의 일정 목록
    
    
    // equals 메서드 재정의
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return courseNo == that.courseNo;
    }

    // hashCode 메서드 재정의
    @Override
    public int hashCode() {
        return Objects.hash(courseNo);
    }
    
    
    
    // CourseListDto 객체로 변환하는 메서드입니다.
    public CourseListDto toCourseListDto() {
        return CourseListDto.builder()
                .courseNo(courseNo) // 과목 번호 설정
                .courseName(courseName) // 과목명 설정
                .credit(credit) // 학점 설정
                .professorName(professor.getUser().getUsername()) // 교수명 설정
                .departmentName(professor.getDepartment().getDepartmentName()) // 학과명 설정
                .courseSchedules(courseSchedule.stream()
                        .map(CourseScheduleEntity::toCourseScheduleDTO) // 강의 일정 목록을 CourseScheduleDTO로 변환
                        .collect(Collectors.toList())) // 리스트로 수집
                .build(); // CourseListDto 객체 생성
    }

    // ProfessorClassListDTO 객체로 변환하는 메서드입니다.
    public ProfessorClassListDTO toListDTO() {
        return ProfessorClassListDTO.builder()
                .course_name(courseName) // 과목명 설정
                .build(); // ProfessorClassListDTO 객체 생성
    }
}