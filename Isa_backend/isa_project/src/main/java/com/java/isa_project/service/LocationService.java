package com.java.isa_project.service;

import java.util.List;

import javax.servlet.Registration;

import com.java.isa_project.model.Location;
import com.java.isa_project.model.LocationType;
import com.java.isa_project.model.Projection;
import com.java.isa_project.model.ProjectionRoom;
import com.java.isa_project.model.ProjectionRoomSeat;
import com.java.isa_project.model.ProjectionTime;
import com.java.isa_project.model.Reservation;

public interface LocationService {

	List<Location> getAll();
	List<Location> getByType(LocationType locationType);
	List<ProjectionTime> getProjectionTimes(Projection projection);
	ProjectionRoom getProjectionRooms(ProjectionTime projectionTime);
	List<ProjectionRoomSeat> getProjectionRoomSeats(ProjectionRoom projectionRoom);
	boolean createReservation(Reservation reservation);
	boolean declineInvitation(int ptId);
	boolean confirmReservation(Reservation reservation);
	List<Reservation> getHistoroy(String email);
	List<Reservation> getActiveReservations(String email);
	boolean cancelReservation(Reservation reservation);
}
