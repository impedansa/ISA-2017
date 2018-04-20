package com.java.isa_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.isa_project.model.Location;
import com.java.isa_project.model.LocationType;

public interface LocationCrudRepository extends JpaRepository<Location, Long>{

	List<Location> findByLocationType(LocationType locationType);
}
