package com.java.isa_project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.concurrent.TimeUnit.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.java.isa_project.model.Location;
import com.java.isa_project.model.LocationType;
import com.java.isa_project.model.Projection;
import com.java.isa_project.model.ProjectionRoom;
import com.java.isa_project.model.ProjectionRoomSeat;
import com.java.isa_project.model.ProjectionTime;
import com.java.isa_project.model.Reservation;
import com.java.isa_project.model.User;
import com.java.isa_project.repository.LocationCrudRepository;
import com.java.isa_project.repository.ProjectionRoomCrudRepository;
import com.java.isa_project.repository.ProjectionRoomSeatCrudRepository;
import com.java.isa_project.repository.ProjectionTimeCrudRepository;
import com.java.isa_project.repository.ReservationCrudRepository;
import com.java.isa_project.repository.UserCrudRepository;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationCrudRepository locationCrudRepository;

	@Autowired
	private ProjectionTimeCrudRepository projectionTimeCrudRepository;

	@Autowired
	private ProjectionRoomCrudRepository projectionRoomCrudRepository;

	@Autowired
	private ProjectionRoomSeatCrudRepository projectionRoomSeatCrudRepository;

	@Autowired
	private ReservationCrudRepository reservationCrudRepository;

	@Autowired
	public JavaMailSender emailSender;
	
	@Autowired
	private UserCrudRepository UserCrudRepository;

	@Override
	public List<Location> getAll() {
		return this.locationCrudRepository.findAll();
	}

	@Override
	public List<Location> getByType(LocationType locationType) {
		List<Location> locs = this.locationCrudRepository.findByLocationType(locationType);
		return locs;
	}

	@Override
	public List<ProjectionTime> getProjectionTimes(Projection projection) {
		List<ProjectionTime> projectionTimes = this.projectionTimeCrudRepository.findByProjectionId(projection.getId());
		return projectionTimes;
	}

	@Override
	public ProjectionRoom getProjectionRooms(ProjectionTime projectionTime) {
		return this.projectionRoomCrudRepository.findByProjectionTimeId(projectionTime.getId()).get(0);
	}

	@Override
	public List<ProjectionRoomSeat> getProjectionRoomSeats(ProjectionRoom projectionRoom) {
		return this.projectionRoomSeatCrudRepository.findByProjectionRoomId(projectionRoom.getId());
	}

	@Override
	public boolean createReservation(Reservation reservation) {
		System.out.println(reservation);
		User us = this.UserCrudRepository.findByEmail(reservation.getUserCreatedReservation().getEmail());
		reservation.setUserCreatedReservation(us);
		if (reservation.getUserCreatedReservation() != null) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(reservation.getUserCreatedReservation().getEmail());
			message.setSubject("Confirm reservation");
			message.setText(
					"You have successfully created the reservation, check it on the \"Active Reservations \" page");
			emailSender.send(message);
		}
		if (reservation.getInvitedFriends() == null) {
			reservation.setInvitedFriends(new ArrayList<>());
		} else {
			for (User u : reservation.getInvitedFriends()) {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(u.getEmail());
				message.setSubject("Reservation invitation");
				message.setText("Please click on the link to confirm invitation for the projection " + reservation.getProjection().getName()+
						" on " + reservation.getProjectionTime().getTime() +" http://localhost:4200/confirm-reservation/"
						+ u.getEmail() + "/" + reservation.getProjectionTime().getId());
				emailSender.send(message);
			}
		}
		ProjectionTime pt = reservation.getProjectionTime();
		System.out.println(pt);
		pt.setNumOfReservedSeats(pt.getNumOfReservedSeats() + reservation.getInvitedFriends().size() + 1);
		this.projectionTimeCrudRepository.save(pt);
		reservation.setProjectionRoom(
				projectionRoomCrudRepository.findByProjectionTimeId(reservation.getProjectionTime().getId()).get(0));
		Reservation r = this.reservationCrudRepository.save(reservation);
		if (r != null)
			return true;
		return false;
	}

	@Override
	public boolean declineInvitation(int ptId) {
		ProjectionTime pt = this.projectionTimeCrudRepository.findById((long)ptId).get();
		pt.setNumOfReservedSeats(pt.getNumOfReservedSeats() - 1);
		return true;
	}

	@Override
	public boolean confirmReservation(Reservation reservation) {
		User us = this.UserCrudRepository.findByEmail(reservation.getUserCreatedReservation().getEmail());
		reservation.setUserCreatedReservation(us);
		ProjectionTime pt = this.projectionTimeCrudRepository.findById(reservation.getProjectionTime().getId()).get();
		reservation.setProjectionTime(pt);
		System.out.println(reservation);
		reservation.setProjectionRoom(
				projectionRoomCrudRepository.findByProjectionTimeId(reservation.getProjectionTime().getId()).get(0));
		reservation.setProjection(pt.getProjection().get(0));
		Reservation r = this.reservationCrudRepository.save(reservation);
		System.out.println(r);
		if (r != null)
			return true;
		return false;
	}

	@Override
	public List<Reservation> getHistoroy(String email) {
		List<Reservation> all = this.reservationCrudRepository.findAll();
		List<Reservation> history = new ArrayList<>();
		Date now = new Date();
		for (Reservation r: all) {
			if(now.after(r.getProjectionTime().getTime()) && r.getUserCreatedReservation().getEmail().equals(email)) {
				history.add(r);
			}
		}
		return history;
	}

	@Override
	public List<Reservation> getActiveReservations(String email) {
		List<Reservation> all = this.reservationCrudRepository.findAll();
		List<Reservation> active= new ArrayList<>();
		Date now = new Date();
		for (Reservation r: all) {
			if(now.before(r.getProjectionTime().getTime()) && r.getUserCreatedReservation().getEmail().equals(email)) {
				active.add(r);
			}
		}
		return active;
	}

	@Override
	public boolean cancelReservation(Reservation reservation) {
		long MAX_DURATION = MILLISECONDS.convert(30, MINUTES);
		long duration = reservation.getProjectionTime().getTime().getTime() - new Date().getTime();
		if(duration >= MAX_DURATION) {
			Reservation r = this.reservationCrudRepository.findById(reservation.getId()).get();
			this.reservationCrudRepository.delete(r);
			return true;
		}
		return false;
	}


}
