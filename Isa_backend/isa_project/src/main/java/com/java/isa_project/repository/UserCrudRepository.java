package com.java.isa_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.isa_project.model.User;

public interface UserCrudRepository extends JpaRepository<User, Long>{

	User findByEmailAndPassword(String email, String password);
	User findByEmail(String email);
}
