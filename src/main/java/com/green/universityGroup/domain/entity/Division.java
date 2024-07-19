package com.green.universityGroup.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Division {
	
	FREE(1,"자유게시판"),
	notice(2,"학과공지"),
	board(3,"학사공지");
	
	private final int no;
	private final String name;
	
}
