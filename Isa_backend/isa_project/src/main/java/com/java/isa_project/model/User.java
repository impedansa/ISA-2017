package com.java.isa_project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User implements Serializable{
	
	private static final long serialVersionUID = -1558702387971201847L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String phoneNumber;
	
	@Column(nullable = false)
	private boolean emailConfirmed = false;

	public User() {}
	
	public User(long id, String email, String password, String firstName, String lastName, String city,
			String phoneNumber, boolean emailConfirmed) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.emailConfirmed = emailConfirmed;
	}
}
