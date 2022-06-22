package com.usermanagement.usermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;

import com.usermanagement.usermanagement.entity.UserEntity;
import com.usermanagement.usermanagement.model.request.UserRequestModel;
import com.usermanagement.usermanagement.model.response.UserResponseModel;
import com.usermanagement.usermanagement.repository.UserRepository;

public class UserServiceTest {
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService;
	
	@Captor
	ArgumentCaptor<UserEntity> user;
	
	@Captor
	ArgumentCaptor<UserEntity> type;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(userService, "firstProperty", "testProperty-1", String.class);
		ReflectionTestUtils.setField(userService, "secondProperty", "testProperty-2", String.class);
	}
	
	@Test
	public void getUser() throws Exception {
		given(userRepository.findByUserId((long) 1))
		        .willReturn((new UserEntity("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date())));
		
		UserResponseModel userResponseModel = userService.getUser((long) 1);
		
		assertNotNull(userResponseModel);
		assertEquals(1, userResponseModel.getUserId());
		assertEquals("Ashebir", userResponseModel.getFirstName());
		
	}
	
	@Test
	public void getAllUsers() throws Exception {
		//Given
		List<UserResponseModel> userResponseModels = new ArrayList<>();
		userResponseModels.add(new UserResponseModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()));
		userResponseModels.add(new UserResponseModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()));
		userResponseModels.add(new UserResponseModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()));
		
		Pageable pageableRequest = PageRequest.of(1, 4, Sort.by("userId").ascending());
		Page<UserEntity> userPage = null;
		
		userPage = userRepository.findAll(pageableRequest);
		
		List<UserEntity> users = userPage.getContent();
		
		given(users).willReturn(users);
		
		//When
		List<UserResponseModel> userResponseModels2 = userService.getAllUsers(1, 10);
		
		//Then
		assertEquals(users, userResponseModels2);
	}
	
	@Test
	public void saveUser() {
		new UserEntity("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date());
		
		given(userRepository.save(Mockito.any()))
		        .willReturn(new UserEntity("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()));
		UserResponseModel savedUser = userService
		        .createUser(new UserRequestModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()));
		assertEquals("Ashebir", savedUser.getFirstName());
		
	}
	
	@Test
	public void updateUser() {
		//Given
		UserResponseModel userResponseModel = new UserResponseModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com",
		        new Date());
		userResponseModel.setUserId(1003);
		given(userRepository.save(Mockito.any()))
		        .willReturn(new UserEntity("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()));
		
		//When
		UserResponseModel userResponseModel1 = userService.updateUser(userResponseModel.getUserId(),
		    new UserRequestModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()));
		
		//Then
		
		then(userRepository).should().save(type.capture());
		assertEquals("Compact SUV", userResponseModel1.getFirstName());
		assertTrue(type.getValue().getUserId() != 0);
	}
}
