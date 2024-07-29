package com.green.universityGroup.service;


import com.green.universityGroup.domain.dto.UserUpdateDTO;

public interface UserService  {

	void updateProfile(long user_no, UserUpdateDTO dto);


}
