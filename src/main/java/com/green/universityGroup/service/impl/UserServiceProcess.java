package com.green.universityGroup.service.impl;

import org.springframework.stereotype.Service;

import com.green.universityGroup.domain.dto.UserSaveDTO;
import com.green.universityGroup.domain.repository.UserEntityRepository;
import com.green.universityGroup.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceProcess implements UserService {

	private final UserEntityRepository repository;
	@Override
	public void save(UserSaveDTO dto) {
		
		
	}

}
