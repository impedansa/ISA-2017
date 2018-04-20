import { Component, OnInit } from '@angular/core';
import {LocationService} from "../../services/location.service";

@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})
export class CinemaComponent implements OnInit {

  private locations: Location[] = [];

  constructor(private locationService: LocationService) { }

  ngOnInit() {
    this.locationService.getCinemas().subscribe(
      (data) => {
        this.locations = JSON.parse(data['_body']);
      }
    );
  }

}
