package com.tekcamp.spring.services.impl;

import com.tekcamp.spring.dao.UserRepository;
import com.tekcamp.spring.exception.ResourceNotFoundException;
import com.tekcamp.spring.model.User;
import com.tekcamp.spring.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	public List<User> getUsers() {
		List<User> returnValue = new ArrayList<User>();
		
		returnValue = (List<User>) userRepository.findAll();
		
		return returnValue;
	}

	@Override
	public Optional<User> getUserById(Long id) {
		Optional<User> returnValue = userRepository.findById(id);
		return returnValue;
	}

	@Override
	public User getUserByEmail(String email) {
		User returnValue = userRepository.findByEmail(email);
		return returnValue;
	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", id));

		userRepository.delete(user);
	}

	@Override
	public void createUser(User user) {
		userRepository.save(user);		
	}

	@Override
	public User updateUser(Long id, User userDetails) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", id));

		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());

		return userRepository.save(user);
	}

}
