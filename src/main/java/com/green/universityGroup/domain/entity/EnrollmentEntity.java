package com.green.universityGroup.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@DynamicUpdate // 엔티티의 변경된 부분만 업데이트하도록 설정합니다.
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 매개변수로 받는 생성자를 생성합니다. 접근 레벨은 PRIVATE입니다.
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성합니다.
@Builder // 빌더 패턴을 사용하여 객체를 생성할 수 있도록 합니다.
@Getter // 모든 필드에 대한 getter 메서드를 자동으로 생성합니다.
@Table(name = "enrollment") // 데이터베이스 테이블과 매핑합니다.
@Entity // JPA 엔티티임을 나타냅니다.
public class EnrollmentEntity {

    @Id // 기본 키를 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임합니다.

    private long enrollment_no; // 수강신청 번호
    
    /*
    @ManyToOne
    @JoinColumn(name = "student_no") // 외래 키를 설정합니다.

    private long enrollmentNo; // 수강신청 번호

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계를 설정하고, 지연 로딩을 사용합니다.
    @JoinColumn(name = "studentNo") // 외래 키를 설정합니다.

    private StudentEntity student; // 학생 정보
    */


    @ManyToOne
    @JoinColumn(name = "course_no") // 외래 키를 설정합니다.
    private CourseEntity course; // 과목 정보
}
