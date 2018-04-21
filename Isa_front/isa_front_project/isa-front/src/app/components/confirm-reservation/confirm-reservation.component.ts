import { Component, OnInit } from '@angular/core';
import {LocationService} from "../../services/location.service";
import {UserService} from "../../services/user.service";
import {Router, ActivatedRoute} from "@angular/router";
declare let swal: any;

@Component({
  selector: 'app-confirm-reservation',
  templateUrl: './confirm-reservation.component.html',
  styleUrls: ['./confirm-reservation.component.css']
})
export class ConfirmReservationComponent implements OnInit {

  private email: string;
  private projectionTimeId: number;

  constructor(private locationService: LocationService, private activatedRoute: ActivatedRoute, private router: Router, private userService: UserService) { }

  ngOnInit() {
    this.email = this.activatedRoute.snapshot.params['email'];
    this.projectionTimeId = this.activatedRoute.snapshot.params['pt'];
  }

  confirm(){
    this.locationService.confirmInvitation(this.email, this.projectionTimeId).subscribe(
      (data) => {
        let rout = this.router;
        swal({title: "Success!", text: "You have confirmed invitation!", type: "success"}).then(function () {
          rout.navigateByUrl('');
        });
      }
    );
  }

  decline(){
    this.locationService.declineInvitation(this.projectionTimeId).subscribe(
      (data) => {
        let rout = this.router;
        swal({title: "Success!", text: "You have declined invitation!", type: "success"}).then(function () {
          rout.navigateByUrl('');
        });
      }
    );
  }
}
