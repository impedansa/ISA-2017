package com.java.isa_project.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = false)
	private String name; 
	
	@Column(nullable = false, unique = false)
	private LocationType locationType;
	
	@Column(nullable = false, unique = false)
	private String address;
	
	@Column(nullable = true, unique = false)
	private String description;
	
	@OneToMany
	@JoinColumn(name="location_id")
	private List<Projection> projections;
	

}
