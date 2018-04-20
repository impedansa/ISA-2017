package com.java.isa_project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.java.isa_project.model.Relationship;
import com.java.isa_project.model.RelationshipStatus;
import com.java.isa_project.model.User;
import com.java.isa_project.repository.RelationshipCrudRepository;
import com.java.isa_project.repository.UserCrudRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserCrudRepository userCrudRepository;
	
	@Autowired
	private RelationshipCrudRepository relationshipCrudRepository;

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public boolean register(User user) {
		User registeredUser = this.userCrudRepository.save(user);
		if (registeredUser != null) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(user.getEmail());
			message.setSubject("Confirm registration");
			message.setText(
					"Please click on the link to login: http://localhost:4200/home/email/" + user.getEmail());
			emailSender.send(message);
			return true;
		}
		return false;

	}

	@Override
	public boolean login(User user) {
		User loggedUser = this.userCrudRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (loggedUser != null && loggedUser.isEmailConfirmed())
			return true;
		return false;
	}

	@Override
	public boolean checkIfEmailExists(String email) {
		User user = this.userCrudRepository.findByEmail(email);
		if (user != null)
			return true;
		return false;
	}

	@Override
	public boolean confirmEmail(String email) {
		User user = this.userCrudRepository.findByEmail(email);
		if (user != null) {
			user.setEmailConfirmed(true);
			this.userCrudRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public User getUser(String email) {
		return this.userCrudRepository.findByEmail(email);
	}

	@Override
	public List<User> getPeople(String email) {
		List<User> allUsers = this.userCrudRepository.findAll();
		List<User> removeUsers = new ArrayList<User>();
		User loggedUser = this.userCrudRepository.findByEmail(email);
		List<Relationship> loggedUser1 = this.relationshipCrudRepository.findByUserOneId(loggedUser.getId());
		List<Relationship> loggedUser2 = this.relationshipCrudRepository.findByUserTwoId(loggedUser.getId());
		for(Relationship r: loggedUser1) {
			for(User u: allUsers) {
				if(u.getId() == r.getUserTwoId() &&
						r.getRelationshipStatus() != RelationshipStatus.BLOCKED &&
						r.getRelationshipStatus() != RelationshipStatus.DECLINED) {
					removeUsers.add(u);
				}
			}
		}
		for(Relationship r: loggedUser2) {
			for(User u: allUsers) {
				if(u.getId() == r.getUserOneId() &&
						r.getRelationshipStatus() != RelationshipStatus.BLOCKED &&
						r.getRelationshipStatus() != RelationshipStatus.DECLINED) {
					if(!removeUsers.contains(u))
						removeUsers.add(u);
				}
			}
		}
		for (User u: removeUsers) {
			allUsers.remove(u);
		}
		allUsers.remove(loggedUser);
		return allUsers;
	}
	
	@Override
	public List<User> getFriends(String email) {
		User loggedUser = this.userCrudRepository.findByEmail(email);
		List<Relationship> loggedUser1 = this.relationshipCrudRepository.findByUserOneId(loggedUser.getId());
		List<Relationship> loggedUser2 = this.relationshipCrudRepository.findByUserTwoId(loggedUser.getId());
		List<User> friends = new ArrayList<User>();
		for(Relationship r: loggedUser1) {
			if(r.getRelationshipStatus() == RelationshipStatus.ACCEPTED) {
				Optional<User> l = this.userCrudRepository.findById(r.getUserTwoId());
				friends.add(l.get());
			}
		}
		for(Relationship r: loggedUser2) {
			if(r.getRelationshipStatus() == RelationshipStatus.ACCEPTED) {
				Optional<User> l = this.userCrudRepository.findById(r.getUserOneId());
				friends.add(l.get());
			}
		}
		return friends;
	}

	@Override
	public List<User> getRequests(String email) {
		User loggedUser = this.userCrudRepository.findByEmail(email);
		List<User> requests = new ArrayList<User>();
		List<Relationship> loggedUser2 = this.relationshipCrudRepository.findByUserTwoId(loggedUser.getId());
		for(Relationship r: loggedUser2) {
			if(r.getRelationshipStatus() == RelationshipStatus.PENDING) {
				Optional<User> l = this.userCrudRepository.findById(r.getUserOneId());
				requests.add(l.get());
			}
		}
		return requests;
	}

	@Override
	public void sendFriendRequest(User sender, User receiver) {
		// before this it was containg only the email and password
		sender = this.userCrudRepository.findByEmail(sender.getEmail());
		Relationship r = this.relationshipCrudRepository.findByUserOneIdAndUserTwoId(sender.getId(), receiver.getId());
		if (r == null) {
			Relationship relationship = new Relationship();
			relationship.setUserOneId(sender.getId());
			relationship.setUserTwoId(receiver.getId());
			relationship.setRelationshipStatus(RelationshipStatus.PENDING);
			this.relationshipCrudRepository.save(relationship);
		}else {
			r.setRelationshipStatus(RelationshipStatus.PENDING);
			this.relationshipCrudRepository.save(r);
		}
		
	}

	@Override
	public void acceptFriendRequest(User sender, User receiver) {
		receiver = this.userCrudRepository.findByEmail(receiver.getEmail());
		Relationship r = this.relationshipCrudRepository.findByUserOneIdAndUserTwoId(sender.getId(), receiver.getId());
		r.setRelationshipStatus(RelationshipStatus.ACCEPTED);
		this.relationshipCrudRepository.save(r);
	}

	@Override
	public void declineFriendRequest(User sender, User receiver) {
		receiver = this.userCrudRepository.findByEmail(receiver.getEmail());
		Relationship r = this.relationshipCrudRepository.findByUserOneIdAndUserTwoId(sender.getId(), receiver.getId());
		r.setRelationshipStatus(RelationshipStatus.DECLINED);
		this.relationshipCrudRepository.save(r);
	}

	@Override
	public void deleteFriend(User deleter, User deleted) {
		User loggedUser = this.userCrudRepository.findByEmail(deleter.getEmail());
		List<Relationship> loggedUser1 = this.relationshipCrudRepository.findByUserOneId(loggedUser.getId());
		List<Relationship> loggedUser2 = this.relationshipCrudRepository.findByUserTwoId(loggedUser.getId());
		
		for (Relationship r: loggedUser1) {
			if (r.getUserTwoId() == deleted.getId() && r.getRelationshipStatus() == RelationshipStatus.ACCEPTED) {
				r.setRelationshipStatus(RelationshipStatus.DECLINED);
				this.relationshipCrudRepository.save(r);
				return;
			}
		}
		
		for (Relationship r: loggedUser2) {
			if (r.getUserOneId() == deleted.getId() && r.getRelationshipStatus() == RelationshipStatus.ACCEPTED) {
				r.setRelationshipStatus(RelationshipStatus.DECLINED);
				this.relationshipCrudRepository.save(r);
				return;
			}
		}
		
	}

	@Override
	public void editPersonalInfo(User user) {
		User u = this.userCrudRepository.findByEmail(user.getEmail());
		user.setId(u.getId());
		user.setEmailConfirmed(u.isEmailConfirmed());
		this.userCrudRepository.save(user);
	}


}
