package com.green.universityGroup.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.green.universityGroup.domain.dto.CourseListDto;
import com.green.universityGroup.domain.dto.ProfessorClassListDTO;
import com.green.universityGroup.domain.entity.CourseEntity;

/**
 * {@link CourseEntity} 엔티티를 관리하기 위한 저장소 인터페이스입니다.
 * 
 * 이 인터페이스는 {@link JpaRepository}를 확장하여 {@link CourseEntity} 엔티티에 대한
 * 기본적인 CRUD 작업과 추가적인 페이징 및 정렬 기능을 제공합니다.
 */
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    /**
     * 교수의 사용자 이름(username)을 기준으로 강좌 목록을 조회합니다.
     * 
     * 이 메소드는 특정 교수의 사용자 이름에 연관된 모든 강좌를 찾기 위해 사용됩니다.
     * Spring Data JPA는 메소드 이름을 분석하여 자동으로 쿼리를 생성합니다.
     *
     * @param username 교수의 사용자 이름
     * @return 주어진 교수의 사용자 이름과 관련된 모든 {@link CourseEntity} 인스턴스의 목록
     */
    List<CourseEntity> findByProfessor_User_Username(String username);
}
