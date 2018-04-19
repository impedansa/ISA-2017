package com.java.isa_project.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.java.isa_project.model.User;
import com.java.isa_project.repository.UserCrudRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserCrudRepository userCrudRepository;

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

}
