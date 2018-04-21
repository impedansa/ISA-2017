package com.java.isa_project.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(targetEntity = User.class, cascade=CascadeType.MERGE)
	@JoinColumn(nullable = false, name="userCreatedReservation")
	private User userCreatedReservation;
	
	@ManyToMany(targetEntity = User.class, cascade=CascadeType.MERGE)
	@JoinColumn(nullable = true)
	private List<User> invitedFriends;
	
	@ManyToOne(targetEntity = Projection.class, cascade=CascadeType.MERGE)
	@JoinColumn(nullable = false)
	private Projection projection;
	
	@ManyToOne(targetEntity = ProjectionTime.class, cascade=CascadeType.MERGE)
	@JoinColumn(nullable = false)
	private ProjectionTime projectionTime;
	
	@ManyToOne(targetEntity = ProjectionRoom.class, cascade=CascadeType.MERGE)
	@JoinColumn(nullable = false)
	private ProjectionRoom projectionRoom;
}
