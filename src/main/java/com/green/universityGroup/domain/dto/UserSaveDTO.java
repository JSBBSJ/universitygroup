package com.green.universityGroup.domain.dto;

import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class UserSaveDTO {

	private String email;
	private String pass;
	private String name;
	private String profile;
	private String role;
}
