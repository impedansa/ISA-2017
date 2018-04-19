package com.java.isa_project.controller;

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
	public boolean login(@RequestBody User user) {
		logger.info("login");
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
	

}
