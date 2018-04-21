import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {LocationService} from "../../services/location.service";
import {ProjectionTime} from "../../beans/projectionTime";
import {Projection} from "../../beans/projection";
import {ProjectionRoom} from "../../beans/projectionRoom";
import {ProjectionRoomSeat} from "../../beans/projectionRoomSeat";
declare let swal: any;

@Component({
  selector: 'app-graphic-reserve',
  templateUrl: './graphic-reserve.component.html',
  styleUrls: ['./graphic-reserve.component.css']
})
export class GraphicReserveComponent implements OnInit {

  private projection: Projection;
  private projectionTime: ProjectionTime;
  private projectionRoom: ProjectionRoom;
  private projectionRoomSeats: ProjectionRoomSeat[] = [];
  private availableReservation: boolean = true;
  private numOfAvailableSeats: number;
  private clickedSeats: boolean = false;
  private selectedElement: any;
  private numOfSelectedSeats: number = 0;

  constructor(private locationService: LocationService, private router: Router) { }

  ngOnInit() {
    this.projection = this.locationService.projectionForReservation;
    this.projectionTime = this.locationService.projectionTimeForReservation;
    this.locationService.getProjectionRooms(this.projectionTime).subscribe(
      (data) => {
        this.projectionRoom = JSON.parse(data['_body']);
        this.locationService.getProjectionRoomSeats(this.projectionRoom).subscribe(
          (data) => {
            this.projectionRoomSeats = JSON.parse(data['_body']);
            this.numOfAvailableSeats = this.projectionRoomSeats.length - this.projectionTime.numOfReservedSeats;
            this.projectionRoomSeats = this.projectionRoomSeats.splice(0, this.numOfAvailableSeats);
            if (this.numOfAvailableSeats == 0){
              this.availableReservation = false;
            }
          }
        );
      }
    );
  }

  continueReservation(){
    if(this.numOfSelectedSeats > 1){
      this.router.navigateByUrl('/home/finish-reservation');
      this.locationService.numberOfFriendsToInvite = this.numOfSelectedSeats;
    }else{
      this.locationService.createReservation(null).subscribe(
        (data) => {
          let rout = this.router;
          swal({title: "Success!", text: "You successfully created reservation!", type: "success"}).then(function () {
            rout.navigateByUrl('/home');
          });
        }
      );

    }
  }

  selectElement(event: any){
    this.selectedElement = event.target;
    let newMatrix: any = "matrix(1 0 0 1 -100 -100)";
    this.selectedElement.setAttributeNS(null, "transform", newMatrix);
    this.numOfSelectedSeats = this.numOfSelectedSeats + 1;
    this.clickedSeats = true;
  }

  resetNumOfSeats(){
    this.numOfSelectedSeats = 0;
    let x = 100;
    let y = 150;
    for(let i = 0; i < this.projectionRoomSeats.length; i++){
      let newMatrix: any = "matrix(1 0 0 1 "+x+" " + y+" " +")";
      document.getElementById('rect'+this.projectionRoomSeats[i].id).setAttributeNS(null, "transform", newMatrix);
      x = x + 300;

    }
    this.clickedSeats = false;
  }
}
