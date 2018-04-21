package com.java.isa_project.model;

import java.io.File;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Projection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = false)
	private String name;

	@Column(nullable = false, unique = false)
	private ProjectionType projectionType;
	
	@Column(nullable = false, unique = false)
	private String genre;
	
	@Column(nullable = false, unique = false)
	private String director;
	
	@Column(nullable = false, unique = false)
	private float duration;
	
	@Column(nullable = true, unique = false)
	private File image;
	
	@Column(nullable = true, unique = false)
	private float avgRate;
	
	@Column(nullable = true, unique = false)
	private String description;
	
	@Column(nullable = true, unique = false)
	private float price;
	
/*	@ManyToOne
	@JoinColumn(name = "location_id")
	@JsonIgnore
	private Location location;*/
	
/*	@ManyToMany(targetEntity = ProjectionTime.class, mappedBy="projection")
	@JoinColumn()
	private List<ProjectionTime> projectionTimes;*/
	
}
