import { Injectable } from '@angular/core';
import {Http, Headers} from "@angular/http";
import {Projection} from "../beans/projection";
import {ProjectionTime} from "../beans/projectionTime";
import {ProjectionRoom} from "../beans/projectionRoom";
import {User} from "../beans/user";
import {Reservation} from "../beans/reservation";

@Injectable()
export class LocationService {

  public locationForReservation: Location;
  public projectionForReservation: Projection;
  public projectionTimeForReservation: ProjectionTime;
  public numberOfFriendsToInvite: number;

  private path = 'http://localhost:8080/location/';

  constructor(private http: Http) { }

  getCinemas(){
    return this.http.get(this.path + 'getCinemas', {withCredentials : true});
  }

  getTheatres(){
    return this.http.get(this.path + 'getTheatres', {withCredentials : true});
  }

  getProjectionTimes(projection: Projection){
    let h = new Headers();
    h.append('Content-type','application/json');
    return this.http.post(this.path + 'getProjectionTimes', JSON.stringify(projection), { headers : h, withCredentials : true });
  }

  getProjectionRooms(projectionTime: ProjectionTime) {
    let h = new Headers();
    h.append('Content-type','application/json');
    return this.http.post(this.path + 'getProjectionRooms', JSON.stringify(projectionTime), { headers : h, withCredentials : true });
  }

  getProjectionRoomSeats(projectionRoom: ProjectionRoom){
    let h = new Headers();
    h.append('Content-type','application/json');
    return this.http.post(this.path + 'getProjectionRoomSeats', JSON.stringify(projectionRoom), { headers : h, withCredentials : true });
  }

  createReservation(invitedFriends: User[]){
    let reservation = new Reservation();
    reservation.invitedFriends = invitedFriends;
    reservation.projection = this.projectionForReservation;
    reservation.projectionTime = this.projectionTimeForReservation;
    let h = new Headers();
    h.append('Content-type','application/json');
    return this.http.post(this.path + 'createReservation', JSON.stringify(reservation), { headers : h, withCredentials : true });
  }

  declineInvitation(projectionTimeId: number){
    return this.http.post(this.path + 'declineInvitation', projectionTimeId);
  }

  confirmInvitation(email: string, projectionTimeId: number){
    let r = new Reservation();
    r.projectionTime = new ProjectionTime();
    r.projectionTime.id = projectionTimeId;
    r.userCreatedReservation = new User();
    r.userCreatedReservation.email = email;
    let h = new Headers();
    h.append('Content-type','application/json');
    return this.http.post(this.path + 'confirmInvitation', JSON.stringify(r), { headers : h, withCredentials : true });
  }

  getHistory(){
      return this.http.get(this.path + '/history', {withCredentials: true});
  }

  getActiveReservations(){
    return this.http.get(this.path + '/activeReservations', {withCredentials: true});
  }
}
