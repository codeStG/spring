package com.tekcamp.spring.controller;

import com.tekcamp.spring.model.User;
import com.tekcamp.spring.services.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getUsers() {
		List<User> returnValue = userService.getUsers();
		
		return returnValue;
	}

	@GetMapping(path = "/{id}")
	public Optional<User> getUser(@PathVariable Long id) {
		Optional<User> returnValue = userService.getUserById(id);
		return returnValue;
	}


	@GetMapping(path = "/email/{email}")
	public User getUser(@PathVariable String email) {
		User returnValue = userService.getUserByEmail(email);

		return returnValue;
	}
	
	@PostMapping
	public void createUser(@RequestBody User user) {
		userService.createUser(user);
	}
	
	@PutMapping(path = "/{id}")
	public void updateUser(@PathVariable(value = "id") Long id, @Validated @RequestBody User userDetails) {
		userService.updateUser(id, userDetails);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
	
	
	
}
