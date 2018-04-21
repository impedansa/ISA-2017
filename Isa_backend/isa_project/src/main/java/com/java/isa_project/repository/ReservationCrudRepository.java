package com.java.isa_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.isa_project.model.Reservation;

public interface ReservationCrudRepository extends JpaRepository<Reservation, Long>{

}
