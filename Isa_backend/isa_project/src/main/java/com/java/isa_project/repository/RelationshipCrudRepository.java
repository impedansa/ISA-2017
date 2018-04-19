package com.java.isa_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.isa_project.model.Relationship;

public interface RelationshipCrudRepository extends JpaRepository<Relationship, Long> {
	List<Relationship> findByUserOneId(Long id);
	List<Relationship> findByUserTwoId(Long id);
	Relationship findByUserOneIdAndUserTwoId(Long userOneId, Long UserTwoId);
}
