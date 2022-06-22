package com.usermanagement.usermanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.usermanagement.usermanagement.entity.UserEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void findByUserId() {
		UserEntity userEntity = userRepository.findByUserId(Mockito.anyLong());
		assertTrue(userEntity != null);
	}
	
	@Test
	public void findAll() {
		Pageable pageableRequest = PageRequest.of(1, 4, Sort.by("userId").ascending());
		Page<UserEntity> userPage = null;
		
		userPage = userRepository.findAll(pageableRequest);
		
		List<UserEntity> users = userPage.getContent();
		assertTrue(users.size() > 0);
	}
	
	@Test
	public void createUser() {
		UserEntity savedUser = userRepository.save(new UserEntity("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()));
		UserEntity returnUser = userRepository.findByEmail("ashebirtse@gmail.com");
		assertTrue(returnUser != null);
		assertEquals(savedUser, returnUser);
	}
	
	@Test
	public void updateUser() {
		UserEntity userEntity = userRepository.findByUserId(Mockito.anyLong());
		userEntity.setFirstName("Ashebir Updated");
		userRepository.save(userEntity);
		UserEntity updateUser = userRepository.findByEmail("ashebirtseupdated@gmail.com");
		assertEquals("Ashebir Updated", updateUser.getFirstName());
	}
}
