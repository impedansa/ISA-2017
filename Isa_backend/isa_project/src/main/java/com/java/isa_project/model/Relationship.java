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
public class Relationship implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//sender
	@Column(nullable = false)
	private Long userOneId;
	
	//receiver
	@Column(nullable = false)
	private Long userTwoId;
	
	@Column(nullable = false)
	private RelationshipStatus relationshipStatus;
}
