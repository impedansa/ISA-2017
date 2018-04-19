import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import {routing} from "./routing/routing";
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {UserService} from "./services/user.service";
import { EmailComponent } from './components/email/email.component';
import { ProfileComponent } from './components/profile/profile.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    EmailComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    routing,
    FormsModule,
    HttpModule,
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
