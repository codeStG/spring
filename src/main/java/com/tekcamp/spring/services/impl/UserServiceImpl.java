package com.tekcamp.spring.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tekcamp.spring.dao.UserRepository;
import com.tekcamp.spring.model.User;
import com.tekcamp.spring.services.UserService;

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

}
