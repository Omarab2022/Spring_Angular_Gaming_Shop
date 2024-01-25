import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
import { AdminModule } from '../admin/admin.module';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';



@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
  
  ],
  exports:[

    HomeComponent,
  ]
})
export class FetureModule { }
