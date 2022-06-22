package com.usermanagement.usermanagement.model.response;

import java.util.Date;

public class UserResponseModel {
	
	private long userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Date dateOfBirth;
	
	public long getUserId() {
		return userId;
	}

	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public UserResponseModel() {
	}
	
	public UserResponseModel(String firstName, String lastName, String email, Date dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		
	}
	
	public UserResponseModel(long l, String string, String string2, String string3, String string4) {
		// TODO Auto-generated constructor stub
	}
}
