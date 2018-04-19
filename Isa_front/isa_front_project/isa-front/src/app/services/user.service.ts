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
    console.log('confirm email ' + email);
    return this.http.post(this.path + 'emailConfirmed', email.trim());
  }
}
