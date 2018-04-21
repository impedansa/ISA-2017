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

import com.java.isa_project.model.User;
import com.java.isa_project.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public boolean register(@RequestBody User user) {
		logger.info("register");
		return userService.register(user);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public boolean login(@RequestBody User user, HttpServletRequest request) {
		logger.info("login");
		request.getSession().setAttribute("user", user);
		return this.userService.login(user);
	}
	
	@CrossOrigin(allowCredentials="false")
	@RequestMapping(value = "/checkEmail", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN)
	public boolean checkEmail(@RequestBody String email) {
		logger.info("check email");
		return this.userService.checkIfEmailExists(email);
	}
	
	@CrossOrigin(allowCredentials="false")
	@RequestMapping(value = "/emailConfirmed", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN)
	public boolean emailConfirmation(@RequestBody String email) {
		logger.info("email confirmation");
		return this.userService.confirmEmail(email);
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public User getUser(HttpServletRequest request) {
		logger.info("get user");
		//this user contains only email and password
		User user = (User) request.getSession().getAttribute("user");
		return this.userService.getUser(user.getEmail());
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request) {
		logger.info("logout");
		request.removeAttribute("user");
	}

	@RequestMapping(value = "/getPeople", method = RequestMethod.GET)
	public List<User> getPeople(HttpServletRequest request) {
		logger.info("get people");
		User user = (User) request.getSession().getAttribute("user");
		return this.userService.getPeople(user.getEmail());
	}
	
	@RequestMapping(value = "/getFriends", method = RequestMethod.GET)
	public List<User> getFriends(HttpServletRequest request) {
		logger.info("friends");
		User user = (User) request.getSession().getAttribute("user");
		return this.userService.getFriends(user.getEmail());
	}
	
	@RequestMapping(value = "/getRequests", method = RequestMethod.GET)
	public List<User> getRequests(HttpServletRequest request) {
		logger.info("email friend requests");
		User user = (User) request.getSession().getAttribute("user");
		return this.userService.getRequests(user.getEmail());
	}
	
	@RequestMapping(value = "/sendFriendRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public void sendFriendRequest(@RequestBody User user, HttpServletRequest request) {
		logger.info("login");
		User u = (User) request.getSession().getAttribute("user");
		this.userService.sendFriendRequest(u, user);
	}
	
	@RequestMapping(value = "/acceptFriendRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public void acceptFriendRequest(@RequestBody User user, HttpServletRequest request) {
		logger.info("login");
		User u = (User) request.getSession().getAttribute("user");
		this.userService.acceptFriendRequest(user, u);
	}
	
	@RequestMapping(value = "/declineFriendRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public void declineFriendRequest(@RequestBody User user, HttpServletRequest request) {
		logger.info("login");
		User u = (User) request.getSession().getAttribute("user");
		this.userService.declineFriendRequest(user, u);
	}
	
	@RequestMapping(value = "/deleteFriend", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public void deleteFriend(@RequestBody User user, HttpServletRequest request) {
		logger.info("login");
		User u = (User) request.getSession().getAttribute("user");
		this.userService.deleteFriend(u, user);
	}
	
	@RequestMapping(value = "/editPersonalInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public void editPersonalInfo(@RequestBody User user, HttpServletRequest request) {
		logger.info("edit personal info");
		User u = (User) request.getSession().getAttribute("user");
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		request.setAttribute("user", u);
		this.userService.editPersonalInfo(user);
	}
}
