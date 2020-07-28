package com.tekcamp.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tekcamp.spring.model.User;

@RestController
@RequestMapping("users")
public class UserController {

	@GetMapping
	public List<User> getUsers() {
		List<User> returnValue = new ArrayList<User>();
		
		return returnValue;
	}
	
	@PostMapping
	public void createUser() {
		
	}
	
	@PutMapping
	public void updateUser() {
		
	}
	
	@DeleteMapping
	public void deleteUser() {
		
	}
	
	
	
}
