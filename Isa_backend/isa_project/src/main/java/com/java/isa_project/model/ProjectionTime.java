package com.java.isa_project.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class ProjectionTime {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private Date time;
	
	@Column(nullable = false)
	private int numOfReservedSeats = 0;
	
	@ManyToMany(targetEntity = Projection.class)
	private List<Projection> projection;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "seatVersion")
	private List<SeatVersions> seatVersions;
}
