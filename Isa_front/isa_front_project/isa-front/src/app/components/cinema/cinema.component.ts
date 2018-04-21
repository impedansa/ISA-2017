import { Component, OnInit } from '@angular/core';
import {LocationService} from "../../services/location.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})
export class CinemaComponent implements OnInit {

  private locations: Location[] = [];

  constructor(private locationService: LocationService, private router: Router) { }

  ngOnInit() {
    this.locationService.getCinemas().subscribe(
      (data) => {
        this.locations = JSON.parse(data['_body']);
      }
    );
  }

  reserveProjection(location){
    this.locationService.locationForReservation = location;
    this.router.navigateByUrl('/home/reserve');
  }
}
