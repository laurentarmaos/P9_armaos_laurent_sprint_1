package com.mediscreen.services;

import java.util.List;

import com.mediscreen.entities.User;

public interface UserService {

	User createUser(User user);
	
	List<User> findAllUsers();
	
	User findById(Long id);
	
	User updateUser(Long id, User user);
	
	void deleteUser(Long id);
}
