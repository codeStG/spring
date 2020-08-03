package com.tekcamp.spring.controller;

import com.tekcamp.spring.dto.UserDto;
import com.tekcamp.spring.model.request.UserRequest;
import com.tekcamp.spring.model.response.UserResponse;
import com.tekcamp.spring.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserResponse> getUsers(@RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="limit", defaultValue="5") int limit){
		List<UserDto> userDtoList = userService.getUsers(page, limit);

		List<UserResponse> userResponseList = new ArrayList<UserResponse>();

		for (UserDto userDto : userDtoList) {
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(userDto, userResponse);
			userResponseList.add(userResponse);
		}

		return userResponseList;
	}

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable Long id) {
		UserDto singleUserDto = userService.getUserById(id);
		UserResponse returnValue = new UserResponse();
		BeanUtils.copyProperties(singleUserDto, returnValue);
		return returnValue;
	}


	@GetMapping(path = "/email/{email}")
	public UserResponse getUser(@PathVariable String email) {
		UserDto singleUserDto = userService.getUserByEmail(email);
		UserResponse returnValue = new UserResponse();
		BeanUtils.copyProperties(singleUserDto, returnValue);

		return returnValue;
	}
	
	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest userRequest) {

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);

		UserDto newUser = userService.createUser(userDto);

		UserResponse returnValue = new UserResponse();
		BeanUtils.copyProperties(newUser, returnValue);

		return returnValue;
	}
	
	@PutMapping(path = "/{id}")
	public UserResponse UserDto(@PathVariable(value = "id") Long id, @Validated @RequestBody UserRequest userDetails) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto updatedUser = userService.updateUser(id, userDto);

		UserResponse returnValue = new UserResponse();
		BeanUtils.copyProperties(updatedUser, returnValue);

		return returnValue;
	}
	
	@DeleteMapping(path = "/{id}")
	public String deleteUser(@PathVariable Long id) {
		String response = userService.deleteUser(id);
		return response;
	}
}
