package com.wheatroot.shoppingapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wheatroot.shoppingapi.model.User;
import com.wheatroot.shoppingapi.repository.UserRepository;
import com.wheatroot.shoppingapi.util.APIResponse;

import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class RegistrationController {
	
	@Autowired
	UserRepository userRepository;
	//Get all user from user table
	@GetMapping("/users")
	public List<User> getAllUser() {
		List<User> userList = userRepository.findAll();
		return userList;
	}
	
	@GetMapping("/users/{id}")
	public User getUserByUserId(@PathVariable int id) {
		User user = userRepository.findById(id).get();
		return user;
	}
	
	//@PostMapping("/adduser")
	@PostMapping(path="/adduser", consumes={"application/json"})
	public ResponseEntity<APIResponse> createNewUser(@RequestBody User user) {
		userRepository.save(user);
		// For demonstration purposes, just returning the user received in the request
        APIResponse apiResponse = new APIResponse(true, "User created successfully");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	@PutMapping(path="/update/{id}", consumes={"application/json"})
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		User updateUser = userRepository.findById(id).get();
		updateUser.setEmail(user.getEmail());
		userRepository.save(updateUser);
		return updateUser;
	}
	
	@DeleteMapping("/user/delete/{id}")
	@ResponseStatus(code=HttpStatus.OK)
	public void removeUser(@PathVariable int id) {
		User user = userRepository.findById(id).get();
		userRepository.delete(user);
	}
}
