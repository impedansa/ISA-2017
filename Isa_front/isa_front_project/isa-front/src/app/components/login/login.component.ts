import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {User} from "../../beans/user";
declare let swal : any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  onSubmitLogin(user: User){
    this.userService.login(user).subscribe(
      (data) => {
        if(data['_body'] == 'true') {
          this.router.navigateByUrl('home');
        }else{
          document.getElementById('err_span').innerHTML = 'Email and password combination is not valid, or email is not confirmed.';
        }
      }
    );
  }

}
