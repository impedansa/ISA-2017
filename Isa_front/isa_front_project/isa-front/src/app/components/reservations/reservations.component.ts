import { Component, OnInit } from '@angular/core';
import {LocationService} from "../../services/location.service";
import {Reservation} from "../../beans/reservation";
declare  let swal: any;

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  private reservations: Reservation[] = [];

  constructor(private locationService: LocationService) { }

  ngOnInit() {
    this.locationService.getActiveReservations().subscribe(
      (data) => {
        this.reservations = JSON.parse(data['_body']);
      }
    );
  }

  cancelReservation(reservation: Reservation){
    this.locationService.cancelReservation(reservation).subscribe(
      (data) => {
        console.log(data['_body']);
        if(data['_body'] == 'true'){
          swal({title: "Success!", text: "You have canceled the reservation!", type: "success"});
          this.reservations.splice(this.reservations.indexOf(reservation), 1);
        }else{
          swal({title: "Warning", text: "You are too late to cancel the reservation.!", type: "warning"});
        }

      }
    );
  }
}
