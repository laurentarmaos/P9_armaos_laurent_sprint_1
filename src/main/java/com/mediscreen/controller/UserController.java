package com.mediscreen.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mediscreen.entities.User;
import com.mediscreen.services.UserService;

@RestController
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/patient/add")
	public ResponseEntity<User> addUser(@RequestBody User user){
		User userCreated = userService.createUser(user);
		
		if(Objects.isNull(userCreated)) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{userId}")
				.buildAndExpand(userCreated.getUserId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	@GetMapping("/patients")
	public List<User> findAllUser() {
		return userService.findAllUsers();
	}
	
	
	@GetMapping("/patient/{id}")
	public User findUser(@PathVariable Long id) {	
		return userService.findById(id);
	}
	
	
	@PutMapping("/patient/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
		User userUpdated = userService.updateUser(id, user);
		
		if(Objects.isNull(userUpdated)) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{userId}")
				.buildAndExpand(userUpdated.getUserId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/patient/delete/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}
}
