package com.tekcamp.spring.services;

import com.tekcamp.spring.dto.UserDto;
import com.tekcamp.spring.model.UserEntity;

import java.util.List;

public interface UserService {
	List<UserDto> getUsers(int page, int limit);

	UserDto getUserById(Long id);

	UserDto getUserByEmail(String email);

	UserDto createUser(UserDto userDto);

	UserEntity updateUser(Long id, UserEntity userDetails);

	void deleteUser(Long id);
}
