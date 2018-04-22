package com.java.isa_project.controller_tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.java.isa_project.controller.UserController;
import com.java.isa_project.model.User;
import com.java.isa_project.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	User mockUser = new User(1, "email@gmail.com", "password", "firstname", "lastname", "city", "phoneNumber",false);
	User mockUser2 = new User(2, "email@gmail.com", "password", "firstname", "lastname", "city", "phoneNumber",false);
	
	String mockUserJSon = "{\"id\":1,\"email\":\"email@gmail.com\",\"password\":\"password\",\"firstName\":\"firstname\","
			+ "\"lastName\":\"lastname\", \"city\":\"city\", \"phoneNumber\":\"phoneNumber\", \"emailConfirmed\":false}";
	String mockUser2JSon = "{\"id\":2,\"email\":\"email@gmail.com\",\"password\":\"password\",\"firstName\":\"firstname\","
			+ "\"lastName\":\"lastname\", \"city\":\"city\", \"phoneNumber\":\"phoneNumber\", \"emailConfirmed\":false}";
	
	
	@Test
	public void registerUser() throws Exception {

		Mockito.when(
				userService.register(Mockito.any())).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/register")
				.accept(MediaType.APPLICATION_JSON).content(mockUserJSon)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals("true", result.getResponse().getContentAsString());
	}
	
	@Test
	public void loginUser() throws Exception {

		Mockito.when(
				userService.login(Mockito.any())).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/login")
				.accept(MediaType.APPLICATION_JSON).content(mockUserJSon)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals("true", result.getResponse().getContentAsString());
	}

	@Test
	public void getUser() throws Exception {

		Mockito.when(
				userService.getUser(Mockito.any())).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/user/getUser").sessionAttr("user", mockUser);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(mockUserJSon, result.getResponse()
				.getContentAsString(), false);
	}


	@Test
	public void getPeople() throws Exception {

		List<User> people = new ArrayList<>();
		people.add(mockUser2);
		Mockito.when(
				userService.getPeople(Mockito.any())).thenReturn(people);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/user/getPeople").sessionAttr("user", mockUser);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		String expect = "["+mockUser2JSon+"]";
		
		JSONAssert.assertEquals(expect, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void getFriends() throws Exception {

		List<User> friends = new ArrayList<>();
		friends.add(mockUser2);
		Mockito.when(
				userService.getFriends(Mockito.any())).thenReturn(friends);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/user/getFriends").sessionAttr("user", mockUser);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		String expect = "["+mockUser2JSon+"]";
		
		JSONAssert.assertEquals(expect, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void getRequests() throws Exception {

		List<User> requests = new ArrayList<>();
		requests.add(mockUser2);
		Mockito.when(
				userService.getRequests(Mockito.any())).thenReturn(requests);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/user/getRequests").sessionAttr("user", mockUser);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		String expect = "["+mockUser2JSon+"]";
		
		JSONAssert.assertEquals(expect, result.getResponse()
				.getContentAsString(), false);
	}
	
}
