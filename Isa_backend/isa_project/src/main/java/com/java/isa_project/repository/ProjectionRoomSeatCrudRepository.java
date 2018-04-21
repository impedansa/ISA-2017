package com.java.isa_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.isa_project.model.ProjectionRoomSeat;

public interface ProjectionRoomSeatCrudRepository extends JpaRepository<ProjectionRoomSeat, Long>{

	List<ProjectionRoomSeat> findByProjectionRoomId(Long id);
	
}
