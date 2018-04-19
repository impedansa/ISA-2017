import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../beans/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  private canEditPasswords: boolean;
  private canEdit: boolean;
  regexp = new RegExp('[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}');
  private user: User;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.userService.getUser().subscribe(
      (data) => {
        this.user = JSON.parse(data['_body']);
      }
    );
    this.canEdit = true;
    this.canEditPasswords = true;
  }

  onEdit(user: User){
    this.userService.editPersonalInfo(user).subscribe(
      (data) => {
        this.router.navigateByUrl('/home');
      }
    );
  }


  checkPasswords(password: string, repassword: string){
    if (password != repassword) {
      document.getElementById('err_pass').innerHTML = 'Passwords must match.';
      document.getElementById('err_repass').innerHTML = 'Passwords must match.';
      this.canEditPasswords = false;
    }else {
      document.getElementById('err_pass').innerHTML = '';
      document.getElementById('err_repass').innerHTML = '';
      this.canEditPasswords = true;
    }
  }

  checkEmail(email: string){
    this.userService.checkEmail(email).subscribe(
      data => {
        if(data['_body'] == 'true') {
          document.getElementById('err_span').innerHTML = 'Email \'' + email + '\' already exists.';
          this.canEdit = false;
        }
        else {
          let test = this.regexp.test(email);
          if (test == false){
            document.getElementById('err_span').innerHTML = 'Email \'' + email + '\' is not valid.';
            this.canEdit = false;
          }else {
            document.getElementById('err_span').innerHTML = '';
            this.canEdit = true;
          }
        }
      }
    );
  }
}
