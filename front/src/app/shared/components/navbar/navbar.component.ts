import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserStorageService } from 'src/app/services/Storage/user-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {



  isCustomerLoggedIn: boolean = UserStorageService.isCustomerLoggedIn();

  // Add a variable to track whether login or register is clicked
  showContent: boolean = true;



  isAdminLoggedIn: boolean = UserStorageService.isAdminLoggedIn();

  constructor(private router: Router) {

  }

  ngOnInit(): void {

    this.router.events.subscribe(event => {
      this.isCustomerLoggedIn = UserStorageService.isCustomerLoggedIn();
      this.isAdminLoggedIn = UserStorageService.isAdminLoggedIn();
    })

    // Update showContent based on login status
    this.showContent = !this.isCustomerLoggedIn && !this.isAdminLoggedIn;

  }

  logout() {
    UserStorageService.signOut();
    this.router.navigateByUrl('login');
  }



  // Modify the methods where login and register are clicked
  onLoginClick() {
    // Other login logic
    this.showContent = false; // Hide content section
    
  }

  onRegisterClick() {
    // Other register logic
    this.showContent = false; // Hide content section
  }



  navigatelogin(path:any){
    this.router.navigate([path]);
  }


  onclick(path:any){

    this.router.navigate([path]);

  }


  OnClickcustomer(path:any){
    this.router.navigateByUrl('/customer/' + path);
  }

}
