package com.tekcamp.spring.services;

import com.tekcamp.spring.dto.UserDto;

import java.util.List;

public interface UserService {
	List<UserDto> getUsers(int page, int limit);

	UserDto getUserById(Long id);

	UserDto getUserByEmail(String email);

	UserDto createUser(UserDto userDto);

	UserDto updateUser(Long id, UserDto userDetails);

	String deleteUser(Long id);
}
