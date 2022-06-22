package com.usermanagement.usermanagement.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.usermanagement.model.response.UserResponseModel;
import com.usermanagement.usermanagement.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserControllerTest.class)
@ActiveProfiles("test")
public class UserControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@Test
	public void getUser() throws Exception {
		given(userService.getUser(Mockito.anyLong()))
		        .willReturn(new UserResponseModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()));
		
		mockMvc.perform(get("/user/1")).andExpect(status().isOk()).andExpect(jsonPath("$").isMap())
		        .andExpect(jsonPath("firstName").value("Ashebir")).andExpect(jsonPath("lastName").value("Tsehaye"))
		        .andExpect(jsonPath("email").value("ashebirtse@gmail.com"))
		        .andExpect(jsonPath("dateOfBirth").value("1994-11-10"));
	}
	
	@Test
	public void getAllUsers() throws Exception {
		List<UserResponseModel> userResponseModels = new ArrayList<>();
		userResponseModels.add(new UserResponseModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()));
		userResponseModels.add(new UserResponseModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()));
		userResponseModels.add(new UserResponseModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()));
		
		given(userService.getAllUsers(1, 10)).willReturn(userResponseModels);
		
		mockMvc.perform(get("/user/")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andDo(print());
	}
	
	@Test
    public void saveUser() throws Exception{
  		UserResponseModel userResponseModel = new UserResponseModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date());
        given(userService.createUser(Mockito.any())).willReturn(userResponseModel);

        mockMvc.perform(
		    post("/user")
		            .content(asJsonString(new UserResponseModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()))));

    }
	
	@Test
	public void updateUser() throws Exception {
		UserResponseModel userResponseModel = new UserResponseModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com",
		        new Date());
		given(userService.updateUser(Mockito.anyLong(), Mockito.any())).willReturn(userResponseModel);
		
		mockMvc.perform(post("/user")
		        .content(asJsonString(new UserResponseModel("Ashebir", "Tsehaye", "ashebirtse@gmail.com", new Date()))));
		
	}

	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
