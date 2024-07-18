package com.green.universityGroup.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
	PROFESSOR("교수")
	,STUDENT("학생")
	;
	
	private final String roleName;
	
	//getter메서드임
	public final String roleName() {
		return roleName;
	}
}
