package com.java.isa_project.service;

import java.util.List;

import com.java.isa_project.model.Location;
import com.java.isa_project.model.LocationType;

public interface LocationService {

	List<Location> getAll();
	List<Location> getByType(LocationType locationType);
}
