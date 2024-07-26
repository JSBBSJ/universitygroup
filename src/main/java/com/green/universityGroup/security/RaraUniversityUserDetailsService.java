package com.green.universityGroup.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.green.universityGroup.domain.repository.UserEntityRepository;

import lombok.RequiredArgsConstructor;


//bean으로 등록해서 DB에 접근해야됨
@Service
@RequiredArgsConstructor
public class RaraUniversityUserDetailsService implements UserDetailsService {

	private final UserEntityRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println(">>>> username"+email);
		
		
		return new CustomUserDetails(repository.findByEmail(email).orElseThrow());
		
	}

}
