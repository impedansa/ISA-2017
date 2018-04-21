package com.java.isa_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.isa_project.model.ProjectionTime;

public interface ProjectionTimeCrudRepository extends JpaRepository<ProjectionTime, Long>{

	List<ProjectionTime> findByProjectionId(Long id);
}
