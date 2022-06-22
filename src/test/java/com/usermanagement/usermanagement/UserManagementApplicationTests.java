package com.usermanagement.usermanagement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.usermanagement.usermanagement.controller.UserControllerTest;
import com.usermanagement.usermanagement.service.UserService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserControllerTest.class)
class UserManagementApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@Test
	void getAllUsersTest() throws Exception {
		
	
	}

}
