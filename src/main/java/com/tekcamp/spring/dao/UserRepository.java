package com.tekcamp.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.tekcamp.spring.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	
}
