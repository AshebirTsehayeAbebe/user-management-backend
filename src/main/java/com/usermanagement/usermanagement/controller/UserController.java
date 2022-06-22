package com.usermanagement.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.usermanagement.model.request.UserRequestModel;
import com.usermanagement.usermanagement.model.response.UserResponseModel;
import com.usermanagement.usermanagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public UserResponseModel createUser(@RequestBody UserRequestModel requestDetail) {
		UserResponseModel returnValue = userService.createUser(requestDetail);
		return returnValue;
		
	}
	
	@GetMapping
	public List<UserResponseModel> getAllUsers(@RequestParam(value = "page", defaultValue = "1") int page,
	        @RequestParam(value = "limit", defaultValue = "50") int limit) {
		
		List<UserResponseModel> returnValue = userService.getAllUsers(page, limit);
		
		return returnValue;
		
	}
	
	@GetMapping(path = "/{userId}")
	public UserResponseModel getUser(@PathVariable Long userId) {
		UserResponseModel returnValue = userService.getUser(userId);
		return returnValue;
	}
	
	@PutMapping(path = "/{userId}")
	public UserResponseModel updateUser(@PathVariable Long userId, @RequestBody UserRequestModel requestDetaill) {
		UserResponseModel returnValue = userService.updateUser(userId, requestDetaill);
		return returnValue;
	}
	
	@DeleteMapping(path = "/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		
		String returnValue = userService.deleteUser(userId);
		return returnValue;
	}
	
}
