package com.green.universityGroup.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.universityGroup.domain.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByUsername(String username);
	
	
	

}
