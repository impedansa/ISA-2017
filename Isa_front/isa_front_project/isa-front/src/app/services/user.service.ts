import { Injectable } from '@angular/core';
import {User} from "../beans/user";
import {Http} from "@angular/http";
import {Headers} from "@angular/http";


@Injectable()
export class UserService {

  private path = 'http://localhost:8080/user/';

  public user: User;

  constructor(private http: Http) { }

  register(user: User){
    let h = new Headers();
    h.append('Content-type','application/json');
    return this.http.post(this.path + 'register', JSON.stringify(user), { headers : h });
  }

  login(user: User){
    let h = new Headers();
    h.append('Content-type','application/json');
    return this.http.post(this.path + 'login', JSON.stringify(user), { headers : h, withCredentials : true });
  }

  checkEmail(email : string){
    return this.http.post(this.path + 'checkEmail', email.trim());
  }

  emailConfirmed(email: string){
    return this.http.post(this.path + 'emailConfirmed', email.trim());
  }

  getUser(){
    return this.http.get(this.path + 'getUser', {withCredentials : true});
  }

  logout(){
    return this.http.post(this.path + 'logout', {withCredentials : true});
  }

  getPeople(){
    return this.http.get(this.path + 'getPeople', {withCredentials : true});
  }

  getFriends(){
    return this.http.get(this.path + 'getFriends', {withCredentials : true});
  }

  getRequests(){
    return this.http.get(this.path + 'getRequests', {withCredentials : true});
  }

  sendFriendRequest(user: User){
    let h = new Headers();
    h.append('Content-type','application/json');
    return this.http.post(this.path + 'sendFriendRequest', JSON.stringify(user), { headers : h, withCredentials : true });
  }

  acceptFriendRequest(user: User){
    let h = new Headers();
    h.append('Content-type','application/json');
    return this.http.post(this.path + 'acceptFriendRequest', JSON.stringify(user), { headers : h, withCredentials : true });
  }

  declineFriendRequest(user: User){
    let h = new Headers();
    h.append('Content-type','application/json');
    return this.http.post(this.path + 'declineFriendRequest', JSON.stringify(user), { headers : h, withCredentials : true });
  }

  deleteFriend(user: User){
    let h = new Headers();
    h.append('Content-type','application/json');
    return this.http.post(this.path + 'deleteFriend', JSON.stringify(user), { headers : h, withCredentials : true });
  }

  editPersonalInfo(user: User){
    let h = new Headers();
    h.append('Content-type','application/json');
    return this.http.post(this.path + 'editPersonalInfo', JSON.stringify(user), { headers : h, withCredentials : true });
  }

}
