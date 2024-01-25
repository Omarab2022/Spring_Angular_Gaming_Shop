import { Component } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
import { UserStorageService } from "src/app/services/Storage/user-storage.service";
import { AuthService } from "src/app/services/auth/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginForm! : FormGroup;

  hidePassword = true;


  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: [null, [Validators.required]],
      password: [null, [Validators.required]],
    });
  }


  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword; 
  }
  
  onSubmit(): void {
    const username = this.loginForm.get('email')!.value;
    const password = this.loginForm.get('password')!.value;
  
    this.authService.login(username, password).subscribe(
      (res) => {
        
        if (UserStorageService.isAdminLoggedIn()) {

          this.router.navigateByUrl('/admin/dashboard');
          
        }else if (UserStorageService.isCustomerLoggedIn()) {
          this.router.navigateByUrl('/customer/dashboard');
        }
        
      },
      (error) => {
        this.snackBar.open('Login failed. Please check your credentials.', 'Dismiss', { duration: 5000 });
        // Handle login error
      }
    );
  }
  

  onclick(path:any){

    this.router.navigate([path]);

  }


}
