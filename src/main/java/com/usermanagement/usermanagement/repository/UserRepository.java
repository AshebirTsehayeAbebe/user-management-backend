package com.usermanagement.usermanagement.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.usermanagement.usermanagement.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
	UserEntity findByUserId(Long id);
	
	

}
