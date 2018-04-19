package com.java.isa_project.service;

import com.java.isa_project.model.User;

public interface UserService {

	public boolean register(User user);
	public boolean login(User user);
	public boolean checkIfEmailExists(String email);
	public boolean confirmEmail(String email);
	
}
