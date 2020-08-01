package com.tekcamp.spring.services.impl;

import com.tekcamp.spring.dao.UserRepository;
import com.tekcamp.spring.dto.UserDto;
import com.tekcamp.spring.model.UserEntity;
import com.tekcamp.spring.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		List<UserEntity> userEntityList = new ArrayList<UserEntity>();

		if(page>0) page--;
		PageRequest pageableRequest = PageRequest.of(page, limit);

		Page<UserEntity> userPageList = userRepository.findAll(pageableRequest);

		userEntityList = userPageList.getContent();

		List<UserDto> userDtoList = new ArrayList<UserDto>();

		for(int i = 0; i<userEntityList.size(); i++) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntityList.get(i), userDto);
			userDtoList.add(userDto);
		}
		
		return userDtoList;
	}

	@Override
	public UserDto getUserById(Long id) {
		UserEntity userEntity;

		if(userRepository.findById(id).isPresent()) {
			userEntity = userRepository.findById(id).get();
		} else {
			throw new NullPointerException("User Not Found");
		}

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto getUserByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	}

	@Override
	public String deleteUser(Long id) {
		UserDto foundDtoUser = new UserDto();

		UserEntity foundUser;
		if(userRepository.findById(id).isPresent()) {
			foundUser = userRepository.findById(id).get();

			BeanUtils.copyProperties(foundUser, foundDtoUser);
			userRepository.deleteById(foundUser.getId());

			return "User " + foundDtoUser.getFirstName() + " " + foundDtoUser.getLastName() + " deleted.";
		} else {
			throw new NullPointerException("User Not Found");
		}
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		UserEntity newUser = new UserEntity();
		BeanUtils.copyProperties(userDto, newUser);

		UserEntity storedUserDetails = userRepository.save(newUser);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);

		return returnValue;
	}

	@Override
	public UserDto updateUser(Long id, UserDto userDetails) {
		UserEntity updateUser = new UserEntity();
		BeanUtils.copyProperties(userDetails, updateUser);

		UserEntity priorUserData;

		if(userRepository.findById(id).isPresent()) {
			priorUserData = userRepository.findById(id).get();

			updateUser.setId(priorUserData.getId());

			UserEntity updatedUserDetails = userRepository.save(updateUser);

			UserDto returnValue = new UserDto();

			BeanUtils.copyProperties(updatedUserDetails, returnValue);

			return returnValue;
		} else {
			throw new NullPointerException("User Not Found");
		}
	}
}
