package com.usermanagement.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.usermanagement.usermanagement.entity.UserEntity;
import com.usermanagement.usermanagement.model.request.UserRequestModel;
import com.usermanagement.usermanagement.model.response.UserResponseModel;
import com.usermanagement.usermanagement.repository.UserRepository;
import com.usermanagement.usermanagement.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserResponseModel createUser(UserRequestModel requestDetail) {
		UserResponseModel returnValue = new UserResponseModel();
		UserEntity userEntity = new UserEntity(null, null, null, null);
		
		BeanUtils.copyProperties(requestDetail, userEntity);
		
		UserEntity savedDetail = userRepository.save(userEntity);
		
		BeanUtils.copyProperties(savedDetail, returnValue);
		return returnValue;
	}
	
	@Override
	public UserResponseModel getUser(Long userId) {
		UserResponseModel returnValue = new UserResponseModel();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new RuntimeException("Invalid User");
		
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}
	
	@Override
	public UserResponseModel updateUser(Long userId, UserRequestModel requestDetaill) {
		UserResponseModel returnValue = new UserResponseModel();
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if (userEntity == null)
			throw new RuntimeException("Invalid User");
		
		BeanUtils.copyProperties(requestDetaill, userEntity);
		UserEntity savedDetail = userRepository.save(userEntity);
		BeanUtils.copyProperties(savedDetail, returnValue);
		return returnValue;
	}
	
	@Override
	public String deleteUser(Long userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new RuntimeException("Invalid User");
		userRepository.delete(userEntity);
		
		return "User Deleted";
	}
	
	@Override
	public List<UserResponseModel> getAllUsers(int page, int limit) {
		List<UserResponseModel> returnValue = new ArrayList<>();
		
		if (page > 0)
			page = page - 1;
		
		Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("userId").ascending());
		Page<UserEntity> userPage = null;
		
		userPage = userRepository.findAll(pageableRequest);
		
		List<UserEntity> users = userPage.getContent();
		
		for (UserEntity userEntity : users) {
			
			UserResponseModel userResponseModel = new UserResponseModel();
			BeanUtils.copyProperties(userEntity, userResponseModel);
			
			returnValue.add(userResponseModel);
		}
		
		return returnValue;
	}
	

}
