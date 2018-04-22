package com.java.isa_project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.isa_project.model.Location;
import com.java.isa_project.model.LocationType;
import com.java.isa_project.model.Projection;
import com.java.isa_project.model.ProjectionRoom;
import com.java.isa_project.model.ProjectionRoomSeat;
import com.java.isa_project.model.ProjectionTime;
import com.java.isa_project.model.Reservation;
import com.java.isa_project.model.User;
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
		logger.info("get cinemas");
		return locationService.getByType(LocationType.CINEMA);
	}
	
	@RequestMapping(value = "/getTheatres", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Location> getTheatres() {
		logger.info("get theatres");
		return locationService.getByType(LocationType.THEATRE);
	}
	
	@RequestMapping(value = "/getProjectionTimes", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public List<ProjectionTime> getProjectionTimes(@RequestBody Projection projection) {
		logger.info("get projection times");
		return locationService.getProjectionTimes(projection);
	}

	@RequestMapping(value = "/getProjectionRooms", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ProjectionRoom getProjectionRooms(@RequestBody ProjectionTime projectionTime) {
		logger.info("get projection rooms");
		return locationService.getProjectionRooms(projectionTime);
	}

	@RequestMapping(value = "/getProjectionRoomSeats", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public List<ProjectionRoomSeat> getProjectionRoomSeats(@RequestBody ProjectionRoom projectionRoom) {
		logger.info("get projection room seats");
		return locationService.getProjectionRoomSeats(projectionRoom);
	}

	@RequestMapping(value = "/createReservation", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON)
	public boolean createReservation(@RequestBody Reservation reservation, HttpServletRequest request) {
		logger.info("create reservation");
		reservation.setUserCreatedReservation((User)request.getSession().getAttribute("user"));
		return locationService.createReservation(reservation);
	}
	
	@CrossOrigin(allowCredentials="false")
	@RequestMapping(value = "/declineInvitation", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN)
	public boolean declineInvitation(@RequestBody String ptId) {
		logger.info("decline reservation");
		return locationService.declineInvitation(Integer.parseInt(ptId));
	}
	@RequestMapping(value = "/confirmInvitation", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON)
	public boolean confirmInvitation(@RequestBody Reservation reservation, HttpServletRequest request) {
		logger.info("confirm reservation");
		return locationService.confirmReservation(reservation);
	}
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public List<Reservation> history(HttpServletRequest request) {
		logger.info("history");
		User user = (User) request.getSession().getAttribute("user");
		return this.locationService.getHistoroy(user.getEmail());
	}
	
	@RequestMapping(value = "/activeReservations", method = RequestMethod.GET)
	public List<Reservation> activeReservations(HttpServletRequest request) {
		logger.info("active reservations");
		User user = (User) request.getSession().getAttribute("user");
		return this.locationService.getActiveReservations(user.getEmail());
	}
	
	@RequestMapping(value = "/cancelReservation", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON)
	public boolean cancelReservation(@RequestBody Reservation reservation, HttpServletRequest request) {
		logger.info("cancel reservation");
		return this.locationService.cancelReservation(reservation);
	}
	
}
