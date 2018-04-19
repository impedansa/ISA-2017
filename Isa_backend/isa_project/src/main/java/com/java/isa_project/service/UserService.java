package com.java.isa_project.service;

import java.util.List;

import com.java.isa_project.model.User;


public interface UserService {

	public boolean register(User user);
	public boolean login(User user);
	public boolean checkIfEmailExists(String email);
	public boolean confirmEmail(String email);
	public User getUser(String email);
	public List<User> getPeople(String email);
	public List<User> getFriends(String email);
	public List<User> getRequests(String email);
	public void sendFriendRequest(User sender, User receiver);
	public void acceptFriendRequest(User sender, User receiver);
	public void declineFriendRequest(User sender, User receiver);
	public void deleteFriend(User deleter, User deleted);
	public void editPersonalInfo(User user);
}
