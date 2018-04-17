package com.java.isa_project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Relationship implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private Long userOneId;
	
	@Id
	private Long userTwoId;
	
	@Column(nullable = false)
	private RelationshipStatus relationshipStatus;
}
