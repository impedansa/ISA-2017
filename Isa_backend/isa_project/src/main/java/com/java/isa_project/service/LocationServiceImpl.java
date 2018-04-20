package com.java.isa_project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.isa_project.model.Location;
import com.java.isa_project.model.LocationType;
import com.java.isa_project.repository.LocationCrudRepository;

@Service
@Transactional
public class LocationServiceImpl implements LocationService{

	@Autowired
	private LocationCrudRepository locationCrudRepository;
	
	@Override
	public List<Location> getAll() {
		return this.locationCrudRepository.findAll();
	}

	@Override
	public List<Location> getByType(LocationType locationType) {
		return this.locationCrudRepository.findByLocationType(locationType);
	}

}
