package com.green.universityGroup.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;// 현재 인증된 사용자의 세부 정보를 가져오는 어노테이션입니다.
import org.springframework.stereotype.Controller;//해당 클래스가 Spring MVC의 컨트롤러임을 나타내는 어노테이션입니다.
import org.springframework.ui.Model;//뷰로 전달할 데이터를 담는 인터페이스입니다.
import org.springframework.web.bind.annotation.GetMapping;//HTTP GET 요청을 처리하는 메서드에 사용하는 어노테이션입니다.
import org.springframework.web.bind.annotation.PostMapping;//HTTP POST 요청을 처리하는 메서드에 사용하는 어노테이션입니다.
import org.springframework.web.bind.annotation.RequestParam;//요청 매개변수를 메서드 파라미터에 바인딩하는 데 사용하는 어노테이션입니다.
import com.green.universityGroup.security.RaraUniversityUserDetails;// 사용자 인증 정보를 담고 있는 커스텀 유저 디테일 클래스입니다.
import com.green.universityGroup.service.CourseService;//수강 과목과 관련된 비즈니스 로직을 처리하는 서비스 클래스입니다.
import lombok.RequiredArgsConstructor;//Lombok 라이브러리에서 제공하는 어노테이션으로, 모든 final 필드를 매개변수로 받는 생성자를 자동으로 생성합니다.

// 수강신청 컨트롤러
@Controller // 이 클래스가 Spring MVC의 컨트롤러임을 나타냅니다.
@RequiredArgsConstructor // Lombok 어노테이션으로, 모든 final 필드를 매개변수로 받는 생성자를 자동으로 생성합니다.
public class EnrolmentController {

    // CourseService를 주입받습니다. 이 서비스는 수강 과목과 관련된 비즈니스 로직을 처리합니다.
    private final CourseService service;

    // 수강 신청 과목 목록을 표시하는 메서드입니다.
    @GetMapping("/enrolment-main")
    public String courseList(@AuthenticationPrincipal RaraUniversityUserDetails user, Model model) {
        // CourseService를 이용해 과목 목록을 모델에 추가합니다.
        service.getCourseList(user, model);
        // views/student/enrolment/enrolment-main.html 파일을 반환하여 해당 뷰를 렌더링합니다.
        return "views/student/enrolment/enrolment-main";
    }
    
    // 수강 신청을 처리하는 메서드입니다.
    @PostMapping("/enrollments")//html의 form태그의  action="@{/enrollments}" 주소화 일치
    public String enrollCourse(@AuthenticationPrincipal RaraUniversityUserDetails userDetails,@RequestParam("courseNo") Long courseNo) {
        // CourseService를 이용해 학생의 수강 신청을 처리합니다.
    	userDetails.getStudentDTO().getStudent_no();
        service.enrollCourse(userDetails.getStudentDTO().getStudent_no(), courseNo);
        // 수강 신청 후에는 다시 수강 목록 페이지로 리다이렉트합니다.
        return "redirect:/enrolment-main";
    }

    // 학생의 수강 목록을 표시하는 메서드입니다.
    @GetMapping("/my-enrollments")
    public String studentEnrollments(@RequestParam("student_no") Long student_no, Model model) {
        // CourseService를 이용해 학생의 수강 목록을 모델에 추가합니다.
        service.getStudentEnrollments(student_no, model);
        // 학생의 수강 목록을 표시하기 위해 views/student/enrolment/enrolment-main.html 파일을 반환합니다.
        return "views/student/enrolment/enrolment-main";
    }
}
