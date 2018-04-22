package com.java.isa_project.controller_tests;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.isa_project.controller.LocationController;
import com.java.isa_project.model.Location;
import com.java.isa_project.model.LocationType;
import com.java.isa_project.model.Reservation;
import com.java.isa_project.service.LocationService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LocationController.class, secure = false)
public class LocationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LocationService locationService;
	
	Location location = new Location(1, "name", LocationType.CINEMA, "address", "description", null);
	Location location1 = new Location(2, "name", LocationType.CINEMA, "address", "description", null);
	
	String locationJson = "{\"id\":1,\"name\":\"name\",\"locationType\":\"CINEMA\",\"address\":\"address\",\"description\":\"description\",\"projections\":null}";
	String locationJson2 = "{\"id\":2,\"name\":\"name\",\"locationType\":\"CINEMA\",\"address\":\"address\",\"description\":\"description\",\"projections\":null}";
	
	@Test
	public void getFriends() throws Exception {

		List<Location> locations = new ArrayList<>();
		locations.add(location1);
		Mockito.when(
				locationService.getAll()).thenReturn(locations);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/location/getAll");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		String expect = "["+locationJson2+"]";
		
		JSONAssert.assertEquals(expect, result.getResponse()
				.getContentAsString(), false);
	}
	
	
	@Test
	public void getCinemas() throws Exception {

		List<Location> locations = new ArrayList<>();
		locations.add(location1);
		Mockito.when(
				locationService.getByType(Mockito.any())).thenReturn(locations);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/location/getCinemas");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		String expect = "["+locationJson2+"]";
		
		JSONAssert.assertEquals(expect, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void getTheatres() throws Exception {

		List<Location> locations = new ArrayList<>();
		locations.add(location1);
		Mockito.when(
				locationService.getByType(Mockito.any())).thenReturn(locations);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/location/getTheatres");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		String expect = "["+locationJson2+"]";
		
		JSONAssert.assertEquals(expect, result.getResponse()
				.getContentAsString(), false);
	}
	
	

}
