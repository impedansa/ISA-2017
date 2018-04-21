import { Component, OnInit } from '@angular/core';
import {LocationService} from "../../services/location.service";
import {Reservation} from "../../beans/reservation";

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
        console.log(this.reservations);
      }
    );
  }
}
