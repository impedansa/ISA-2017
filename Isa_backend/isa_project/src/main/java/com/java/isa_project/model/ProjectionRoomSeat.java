package com.java.isa_project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class ProjectionRoomSeat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private float X;
	
	@Column(nullable = false)
	private float Y;
	
	@ManyToOne
	@JoinColumn(name = "projection_room_id")
	private ProjectionRoom projectionRoom;
}
