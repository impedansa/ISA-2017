import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {LocationService} from "../../services/location.service";
import {Router} from "@angular/router";
import {User} from "../../beans/user";
declare let swal: any;

@Component({
  selector: 'app-finish-reservation',
  templateUrl: './finish-reservation.component.html',
  styleUrls: ['./finish-reservation.component.css']
})
export class FinishReservationComponent implements OnInit {

  private friends: User[] = [];
  private numOfFriendsToInvite;
  private invitedFriends: User[] = [];
  private invited: boolean = false;
  constructor(private locationService: LocationService, private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.userService.getFriends().subscribe(
      (data) => {
        this.friends = JSON.parse(data['_body']);
      }
    );
    this.numOfFriendsToInvite = this.locationService.numberOfFriendsToInvite - 1;
  }

  inviteFriends(user: User){
    this.numOfFriendsToInvite = this.numOfFriendsToInvite -1;

    if (this.numOfFriendsToInvite == 0){
      this.invited = true;
    }

    this.friends.splice(this.friends.indexOf(user),1);
    this.invitedFriends.push(user);
  }

  reset(){
    this.userService.getFriends().subscribe(
      (data) => {
        this.friends = JSON.parse(data['_body']);
      }
    );
    this.numOfFriendsToInvite = this.locationService.numberOfFriendsToInvite - 1;
    this.invited = false;
  }

  finish(){
    this.locationService.createReservation(this.invitedFriends).subscribe(
      (data) => {
        let rout = this.router;
        swal({title : "Success!", text : "You successfully create reservation!", type : "success"}).then(function(){
          rout.navigateByUrl('/home');
        });
      }
    );
  }
}
