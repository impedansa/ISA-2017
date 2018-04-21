import { Component, OnInit } from '@angular/core';
import {LocationService} from "../../services/location.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-theatre',
  templateUrl: './theatre.component.html',
  styleUrls: ['./theatre.component.css']
})
export class TheatreComponent implements OnInit {

  private locations: Location[] = [];

  constructor(private locationService: LocationService, private router: Router) { }

  ngOnInit() {
    this.locationService.getTheatres().subscribe(
      (data) => {
        this.locations = JSON.parse(data['_body']);
      }
    );
  }

  reserveProjection(location: Location){
    this.locationService.locationForReservation = location;
    this.router.navigateByUrl('/home/reserve');
  }
}
