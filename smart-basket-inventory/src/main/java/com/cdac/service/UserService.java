package com.cdac.service;

import java.util.List;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.UserRequestDto;
import com.cdac.dto.UserResponseDto;

public interface UserService {

	public List<UserResponseDto> getAllUserDetails();
	public UserResponseDto getUserWithId(Long id);
	public ApiResponse addUser(UserRequestDto dto);
	public ApiResponse deleteUser(Long id);
	public UserResponseDto updateUserWithId(UserRequestDto dto,Long id);
	
	
}
