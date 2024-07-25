package com.green.universityGroup.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
// 필요한 패키지를 임포트합니다.
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.universityGroup.domain.dto.CourseListDto;
import com.green.universityGroup.security.CustomUserDetails;
import com.green.universityGroup.service.CourseService;

import lombok.RequiredArgsConstructor;

// 수강신청 컨트롤러
@Controller // 이 클래스가 Spring MVC의 컨트롤러임을 나타냅니다.
@RequiredArgsConstructor // Lombok 어노테이션으로, 모든 final 필드를 매개변수로 받는 생성자를 자동으로 생성합니다.
public class EnrolmentController {

    private final CourseService service; // CourseService를 주입받습니다. 이 서비스는 수강 과목과 관련된 비즈니스 로직을 처리합니다.
    
    // 수강 신청 과목 목록을 표시하는 메서드입니다.
    @GetMapping("/enrolment-main")
    public String courseList(@AuthenticationPrincipal CustomUserDetails user, Model model) {
        // CourseService를 이용해 과목 목록을 모델에 추가합니다.
        service.getCourseList(user, model);
        // views/student/enrolment/enrolment-main.html 파일을 반환하여 해당 뷰를 렌더링합니다.
        return "views/student/enrolment/enrolment-main";
    }
    
    // 수강 신청을 처리하는 메서드입니다.
    @PostMapping("/enroll")
    public String enrollCourse(@RequestParam("student_no") Long student_no, @RequestParam("course_no") Long course_no) {
        // CourseService를 이용해 학생의 수강 신청을 처리합니다.
        service.enrollCourse(student_no, course_no);
        // 수강 신청 후에는 다시 수강 목록 페이지로 리다이렉트합니다.
        return "redirect:/enrolment-main";
    }

    // 학생의 수강 목록을 표시하는 메서드입니다.
    @GetMapping("/my-enrollments")
    public String studentEnrollments(@RequestParam("student_no")  Long student_no, Model model) {
        // CourseService를 이용해 학생의 수강 목록을 모델에 추가합니다.
        service.getStudentEnrollments(student_no, model);
        // 학생의 수강 목록을 표시하기 위해 views/student/enrolment/enrolment-main.html 파일을 반환합니다.
        return "views/student/enrolment/enrolment-main";
    }
}
