import {Routes, RouterModule} from "@angular/router";
import {LoginComponent} from "../components/login/login.component";
import {RegisterComponent} from "../components/register/register.component";
import {HomeComponent} from "../components/home/home.component";
import {EmailComponent} from "../components/email/email.component";
import {ProfileComponent} from "../components/profile/profile.component";
import {EditUserComponent} from "../components/edit-user/edit-user.component";
import {CinemaComponent} from "../components/cinema/cinema.component";
import {TheatreComponent} from "../components/theatre/theatre.component";
import {ReservationsComponent} from "../components/reservations/reservations.component";
import {HistoryComponent} from "../components/history/history.component";
import {ReserveComponent} from "../components/reserve/reserve.component";
import {GraphicReserveComponent} from "../components/graphic-reserve/graphic-reserve.component";
import {FinishReservationComponent} from "../components/finish-reservation/finish-reservation.component";
import {ConfirmReservationComponent} from "../components/confirm-reservation/confirm-reservation.component";


const APP_ROUTES: Routes = [
  {path: '', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path:'confirm-reservation/:email/:pt',component:ConfirmReservationComponent},
  {path: 'home', component: HomeComponent,
    children:[
      {path:'',component:ProfileComponent},
      {path:'email/:email',component:EmailComponent},
      {path:'edit',component:EditUserComponent},
      {path:'cinema',component:CinemaComponent},
      {path:'theatre',component:TheatreComponent},
      {path:'reservations',component:ReservationsComponent},
      {path:'history',component:HistoryComponent},
      {path:'reserve',component:ReserveComponent},
      {path:'seats',component:GraphicReserveComponent},
      {path:'finish-reservation',component:FinishReservationComponent}

      ]}

];

export const routing = RouterModule.forRoot(APP_ROUTES);
