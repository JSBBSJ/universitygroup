package com.green.universityGroup.service;

import org.springframework.ui.Model;

import com.green.universityGroup.domain.dto.CourseListDto;
import com.green.universityGroup.security.CustomUserDetails;

/**
 * CourseService는 과목 목록을 가져오고, 수강 신청을 처리하는 서비스 인터페이스입니다.
 */
public interface CourseService {
  
      void getCourseList(String username, Model model);
  
    /**
     * 모든 과목 목록을 가져와서 모델에 추가하는 메서드입니다.
     * 
     * @param dto - 과목 목록을 필터링하는 데 사용할 DTO 객체입니다.
     * @param model - 뷰에 전달할 모델 객체입니다.
     */
    void getCourseList(CourseListDto dto, Model model);

    /**
     * 학생이 특정 과목에 수강 신청을 하는 메서드입니다.
     * 
     * @param studentId - 학생의 ID입니다.
     * @param courseId - 과목의 ID입니다.
     */
    void enrollCourse(Long studentId, Long courseId);

    /**
     * 특정 학생의 수강 목록을 가져와서 모델에 추가하는 메서드입니다.
     * 
     * @param studentId - 학생의 ID입니다.
     * @param model - 뷰에 전달할 모델 객체입니다.
     */
    void getStudentEnrollments(Long studentId, Model model);

	void getCourseList(CustomUserDetails user, Model model);

}
