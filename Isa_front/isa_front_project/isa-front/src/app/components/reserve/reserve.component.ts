import { Component, OnInit } from '@angular/core';
import {LocationService} from "../../services/location.service";
import {Projection} from "../../beans/projection";
import {ProjectionTime} from "../../beans/projectionTime";
import {DatePipe} from "@angular/common";
import {Router} from "@angular/router";

@Component({
  selector: 'app-reserve',
  templateUrl: './reserve.component.html',
  styleUrls: ['./reserve.component.css']
})
export class ReserveComponent implements OnInit {

  private location: Location;
  private times: boolean = false;
  private projectionTimes: ProjectionTime[] = [];
  private showTimeForProjection: Projection;

  constructor(private locationService: LocationService, private router: Router) { }

  ngOnInit() {
    this.location = this.locationService.locationForReservation;

  }

  chooseProjection(projection: Projection){
    this.times = true;
    this.showTimeForProjection = projection;
    this.locationService.getProjectionTimes(projection).subscribe(
      (data) => {
        this.projectionTimes = JSON.parse(data['_body']);
      }
    );
  }

  closeTimes(){
    this.times = false;
    this.showTimeForProjection = null;
  }

  reserveSeat(projectionTime: ProjectionTime){
    this.locationService.projectionTimeForReservation = projectionTime;
    this.locationService.projectionForReservation = this.showTimeForProjection;
    this.router.navigateByUrl('/home/seats');
  }
}
