package com.green.universityGroup.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.green.universityGroup.domain.entity.UserEntity;

import lombok.Getter;

@Getter //principal 에서 확인가능
public class CustomUserDetails extends User{
	private static final long serialVersionUID = 1L;

	//principal에서 확인하기 위해 추가로 등록할 수 있다
	//password 는 등록x
	private String email; //=username
	private String name; //한글이름
	
	//첫번째 인증객체는 
	public CustomUserDetails(UserEntity entity){
		super(entity.getEmail(), entity.getPassword(),
				entity.getRoles().stream()
				.map(role->new SimpleGrantedAuthority("ROLE_"+role))
				.collect(Collectors.toSet()));
		//추가 등록하는건 아래서 초기화 해주면됨
		email=entity.getEmail();
		name=entity.getUsername();
	}
}
