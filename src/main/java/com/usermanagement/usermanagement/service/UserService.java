package com.usermanagement.usermanagement.service;

import java.util.List;

import com.usermanagement.usermanagement.model.request.UserRequestModel;
import com.usermanagement.usermanagement.model.response.UserResponseModel;

public interface UserService {
	
	UserResponseModel createUser(UserRequestModel requestDetail);
	
	UserResponseModel getUser(Long userId);
	
	UserResponseModel updateUser(Long userId, UserRequestModel requestDetaill);
	
	String deleteUser(Long userId);
	
	List<UserResponseModel> getAllUsers(int page, int limit);
	
	
}
