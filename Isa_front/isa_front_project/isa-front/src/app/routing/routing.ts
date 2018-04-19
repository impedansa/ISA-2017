import {Routes, RouterModule} from "@angular/router";
import {LoginComponent} from "../components/login/login.component";
import {RegisterComponent} from "../components/register/register.component";
import {HomeComponent} from "../components/home/home.component";
import {EmailComponent} from "../components/email/email.component";
import {ProfileComponent} from "../components/profile/profile.component";


const APP_ROUTES: Routes = [
  {path: '', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'home', component: HomeComponent,
    children:[
      {path:'',component:ProfileComponent},
      {path:'email/:email',component:EmailComponent}
      ]}

];

export const routing = RouterModule.forRoot(APP_ROUTES);
