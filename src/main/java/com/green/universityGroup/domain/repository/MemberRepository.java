package com.green.universityGroup.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.universityGroup.domain.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

}
