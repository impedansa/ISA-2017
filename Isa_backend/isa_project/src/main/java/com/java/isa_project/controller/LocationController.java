package com.java.isa_project.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.isa_project.model.Location;
import com.java.isa_project.model.LocationType;
import com.java.isa_project.service.LocationService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/location")
public class LocationController {

private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private LocationService locationService;
	
	@Autowired
	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Location> getAll() {
		logger.info("get all locations");
		return locationService.getAll();
	}
	
	@RequestMapping(value = "/getCinemas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Location> getCinemas() {
		logger.info("get all locations");
		return locationService.getByType(LocationType.CINEMA);
	}
	
	@RequestMapping(value = "/getTheatres", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Location> getTheatres() {
		logger.info("get all locations");
		return locationService.getByType(LocationType.THEATRE);
	}
}
