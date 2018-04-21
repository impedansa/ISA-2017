import { Component, OnInit } from '@angular/core';
import {Reservation} from "../../beans/reservation";
import {LocationService} from "../../services/location.service";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  private reservations: Reservation[] = [];

  constructor(private locationService: LocationService) { }

  ngOnInit() {
    this.locationService.getHistory().subscribe(
      (data) => {
        this.reservations = JSON.parse(data['_body']);
        console.log(this.reservations);
      }
    );
  }

}
