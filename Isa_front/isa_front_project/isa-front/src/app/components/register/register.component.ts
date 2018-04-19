import {Component, OnInit, ViewChild} from '@angular/core';
import {User} from "../../beans/user";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
declare let swal : any;

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  canRegister: boolean;
  canRegisterPasswords: boolean;
  regexp = new RegExp('[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}');

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  onSubmitRegistration(user: User){
    swal({title: "Registering ...", type: "info", showConfirmButton: false});
    this.userService.register(user).subscribe(
      (data) => {
        let rout = this.router;
        if(data['_body'] == 'true') {
          swal({title : "Success!", text : "You successfully registered!", type : "success"}).then(function(){
            rout .navigateByUrl('');
          });
        }
      }
    );
  }

  checkPasswords(password: string, repassword: string){
    if (password != repassword) {
      document.getElementById('err_pass').innerHTML = 'Passwords must match.';
      document.getElementById('err_repass').innerHTML = 'Passwords must match.';
      this.canRegisterPasswords = false;
    }else {
      document.getElementById('err_pass').innerHTML = '';
      document.getElementById('err_repass').innerHTML = '';
      this.canRegisterPasswords = true;
    }
  }

  checkEmail(email: string){
    this.userService.checkEmail(email).subscribe(
      data => {
        if(data['_body'] == 'true') {
          document.getElementById('err_span').innerHTML = 'Email \'' + email + '\' already exists.';
          this.canRegister = false;
        }
        else {
          let test = this.regexp.test(email);
          if (test == false){
            document.getElementById('err_span').innerHTML = 'Email \'' + email + '\' is not valid.';
            this.canRegister = false;
          }else {
            document.getElementById('err_span').innerHTML = '';
            this.canRegister = true;
          }
        }
      }
    );
  }
}
