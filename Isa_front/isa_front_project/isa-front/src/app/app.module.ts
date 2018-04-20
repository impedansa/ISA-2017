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
import {TabModule} from 'angular-tabs-component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { CinemaComponent } from './components/cinema/cinema.component';
import { TheatreComponent } from './components/theatre/theatre.component';
import {LocationService} from "./services/location.service";
import { HistoryComponent } from './components/history/history.component';
import { ReservationsComponent } from './components/reservations/reservations.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    EmailComponent,
    ProfileComponent,
    NavbarComponent,
    EditUserComponent,
    CinemaComponent,
    TheatreComponent,
    HistoryComponent,
    ReservationsComponent
  ],
  imports: [
    BrowserModule,
    routing,
    FormsModule,
    HttpModule,
    TabModule
  ],
  providers: [UserService, LocationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
