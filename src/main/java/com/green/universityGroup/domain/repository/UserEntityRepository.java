package com.green.universityGroup.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.universityGroup.domain.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

}
