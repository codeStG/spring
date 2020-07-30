package com.tekcamp.spring.services;

import com.tekcamp.spring.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
	List<User> getUsers();

	Optional<User> getUserById(Long id);

	User getUserByEmail(String email);

	void deleteUser(Long id);

	void createUser(User user);

	User updateUser(Long id, User userDetails);
}
