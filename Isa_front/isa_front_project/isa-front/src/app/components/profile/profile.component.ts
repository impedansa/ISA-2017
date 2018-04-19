import { Component, OnInit } from '@angular/core';
import {User} from "../../beans/user";
import {UserService} from "../../services/user.service";
declare let swal : any;

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  private personal: boolean;
  private friends: boolean;
  private other: boolean;
  private requests: boolean;
  private editModal: boolean;

  private user: User = new User();
  private userFriends: User[] = [];
  private people: User[] = [];
  private userRequests: User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getUser().subscribe(
      (data) => {
       this.user = JSON.parse(data['_body']);
      }
    );
    this.userService.getPeople().subscribe(
      (data) => {
        this.people = JSON.parse(data['_body']);
      }
    );
    this.userService.getFriends().subscribe(
      (data) => {
        this.userFriends = JSON.parse(data['_body']);
      }
    );
    this.userService.getRequests().subscribe(
      (data) => {
        this.userRequests = JSON.parse(data['_body']);
      }
    );
    this.personal = true;
    this.friends = false;
    this.other = false;
    this.requests = false;
    this.editModal = false;
  }

  sendRequest(user: User){
    this.userService.sendFriendRequest(user).subscribe(
      (data) => {
        swal({title : "Success!", text : "Friend request sent.", type : "success"});
        this.people.splice(this.people.indexOf(user), 1);
      }
    );
  }

  acceptRequest(user: User){
    this.userService.acceptFriendRequest(user).subscribe(
      (data) => {
        swal({title : "Success!", text : "Friend request accepted.", type : "success"});
        this.userRequests.splice(this.people.indexOf(user), 1);
        this.userFriends.push(user);
      }
    );
  }

  declineRequest(user: User){
    this.userService.declineFriendRequest(user).subscribe(
      (data) => {
        swal({title : "Success!", text : "Friend request declined.", type : "info"});
        this.userRequests.splice(this.people.indexOf(user), 1);
        this.people.push(user);
      }
    );
  }

  deleteFriend(user){
    this.userService.deleteFriend(user).subscribe(
      (data) => {
        swal({title : "Success!", text : "Friend has been deleted.", type : "info"});
        this.userFriends.splice(this.people.indexOf(user), 1);
        this.people.push(user);
      }
    );
  }

  showPersonal(){
    this.personal = true;
    this.friends = false;
    this.other = false;
    this.requests = false;
  }

  showFriends(){
    this.userService.getFriends().subscribe(
      (data) => {
        this.userFriends = JSON.parse(data['_body']);
      }
    );
    this.personal = false;
    this.friends = true;
    this.other = false;
    this.requests = false;
  }

  showOther(){
    this.userService.getPeople().subscribe(
      (data) => {
        this.people = JSON.parse(data['_body']);
      }
    );
    this.personal = false;
    this.friends = false;
    this.other = true;
    this.requests = false;
  }

  showRequests() {
    this.userService.getRequests().subscribe(
      (data) => {
        this.userRequests = JSON.parse(data['_body']);
      }
    );
    this.personal = false;
    this.friends = false;
    this.other = false;
    this.requests = true;
  }
}
