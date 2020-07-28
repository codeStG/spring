package com.tekcamp.spring.services;

import java.util.List;

import com.tekcamp.spring.model.User;

public interface UserService {
	List<User> getUsers();

	void createUser();
}
