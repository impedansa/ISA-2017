import { Injectable } from '@angular/core';
import {Http} from "@angular/http";

@Injectable()
export class LocationService {

  private path = 'http://localhost:8080/location/';

  constructor(private http: Http) { }

  getCinemas(){
    return this.http.get(this.path + 'getCinemas', {withCredentials : true});
  }

  getTheatres(){
    return this.http.get(this.path + 'getTheatres', {withCredentials : true});
  }

}
