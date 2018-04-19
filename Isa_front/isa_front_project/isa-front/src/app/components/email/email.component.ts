import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private userService: UserService) { }

  ngOnInit() {
    console.log('should confirm email');
    this.userService.emailConfirmed(this.activatedRoute.snapshot.params['email']).subscribe(
      (data) => {
        console.log('email confirmation well done');
        this.router.navigateByUrl('');
      }
    );
  }

}
