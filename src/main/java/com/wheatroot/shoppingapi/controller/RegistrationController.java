package com.wheatroot.shoppingapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.wheatroot.shoppingapi.model.User;
import com.wheatroot.shoppingapi.repository.UserRepository;
import com.wheatroot.shoppingapi.util.APIResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class RegistrationController {
	private final UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	//Get all user from user table
	@GetMapping("/users")
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserByUserId(@PathVariable int id) {
	    try {
	        Optional<User> user = userRepository.findById(id);
	        if (user.isPresent()) {
	            APIResponse apiResponse = new APIResponse(true, "User retrieved successfully");
	            return ResponseEntity.ok(apiResponse);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        // Log the error for debugging
	        e.printStackTrace(); // Replace with proper logging implementation
	        // Consider returning more specific error messages based on exception type
	        return ResponseEntity.internalServerError().build();
	    }
	}
	
	@PostMapping(path = "/adduser", consumes = { "application/json" })
	public ResponseEntity<APIResponse> createNewUser(@Valid @RequestBody User user) {
	    try {
	        userRepository.save(user);
	        APIResponse apiResponse = new APIResponse(true, "User created successfully");
	        return ResponseEntity.ok(apiResponse);
	    } catch (DataIntegrityViolationException e) {
	        // Handle unique constraint violations (e.g., duplicate email)
	        return ResponseEntity.badRequest().body(new APIResponse(false, "User creation failed: Email already exists"));
	    } catch (Exception e) {
	        // Log the error for debugging
	        e.printStackTrace(); // Replace with proper logging implementation
	        return ResponseEntity.internalServerError().body(new APIResponse(false, "Internal server error"));
	    }
	}
	
	@PutMapping(path = "/update/{id}", consumes = { "application/json" })
	public ResponseEntity<User> updateUser(@PathVariable int id, @Valid @RequestBody User user) {
	    Optional<User> existingUser = userRepository.findById(id);
	    if (!existingUser.isPresent()) {
	        return ResponseEntity.notFound().build(); // Return 404 Not Found
	    }
	    existingUser.get().setEmail(user.getEmail()); // Safe access with .get() as we know it exists
	    userRepository.save(existingUser.get());
	    return ResponseEntity.ok(existingUser.get()); // Return 200 OK with updated user
	}
	
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<Void> removeUser(@PathVariable int id) {
	    try {
	        userRepository.deleteById(id); // Use deleteById for direct deletion
	        return ResponseEntity.ok().build(); // Return 200 OK on success
	    } catch (EmptyResultDataAccessException e) {
	        return ResponseEntity.notFound().build(); // Return 404 Not Found if user not found
	    } catch (Exception e) { // Handle other potential exceptions
	        // Log the error for debugging
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 Internal Server Error
	    }
	}
}
