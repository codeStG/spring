package com.tekcamp.spring.services.impl;

import com.tekcamp.spring.dao.UserRepository;
import com.tekcamp.spring.dto.UserDto;
import com.tekcamp.spring.exception.ResourceNotFoundException;
import com.tekcamp.spring.model.UserEntity;
import com.tekcamp.spring.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
		Optional<UserEntity> userEntity = userRepository.findById(id);
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
	public void deleteUser(Long id) {
		UserEntity userEntity = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", id));

		userRepository.delete(userEntity);
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
	public UserEntity updateUser(Long id, UserEntity userDetails) {
		UserEntity userEntity = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", id));

		userEntity.setFirstName(userDetails.getFirstName());
		userEntity.setLastName(userDetails.getLastName());
		userEntity.setEmail(userDetails.getEmail());
		userEntity.setPassword(userDetails.getPassword());

		return userRepository.save(userEntity);
	}

//	@Override
//	public UserDto updateUser(Long id, UserEntity userDetails) {
//		UserEntity updatedUser = userRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
//
//		UserDto userDto = new UserDto();
//		BeanUtils.copyProperties(updatedUser, userDto);
//
//		userDto.setFirstName(userDetails.getFirstName());
//		userDto.setLastName(userDetails.getLastName());
//		userDto.setEmail(userDetails.getEmail());
//		userDto.setPassword(userDetails.getPassword());
//
//		UserEntity storedUserDetails = userRepository.save(updatedUser);
//
//
//		return userRepository.save(userEntity);
//	}

}
