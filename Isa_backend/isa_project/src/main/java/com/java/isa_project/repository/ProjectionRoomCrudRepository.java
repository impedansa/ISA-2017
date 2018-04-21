package com.java.isa_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.isa_project.model.ProjectionRoom;

public interface ProjectionRoomCrudRepository extends JpaRepository<ProjectionRoom, Long>{

	List<ProjectionRoom> findByProjectionTimeId(Long id);
}
