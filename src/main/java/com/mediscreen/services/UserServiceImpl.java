package com.mediscreen.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mediscreen.entities.User;
import com.mediscreen.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User createUser(User dto) {
		User entity = new User();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setGender(dto.getGender());
		entity.setPhone(dto.getPhone());
		entity.setAddress(dto.getAddress());
		
		userRepository.save(entity);
		
		return entity;
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User updateUser(Long id, User dto) {
		User entity = userRepository.findById(id).get();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setGender(dto.getGender());
		entity.setPhone(dto.getPhone());
		entity.setAddress(dto.getAddress());
		
		userRepository.save(entity);
		
		return entity;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}

}
