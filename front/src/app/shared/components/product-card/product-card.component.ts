import { Component, Input } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Route, Router } from '@angular/router';
import { AdminService } from 'src/app/admin/service/admin.service';
import { DashboardComponent } from 'src/app/admin/Components/dashboard/dashboard.component';
import { ProductCommunicationService } from './products-card.service';
import { CustomerService } from 'src/app/customer/service/customer.service';
@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.scss']
})
export class ProductCardComponent {





  constructor(private router: Router, private adminservice: AdminService, 

    private fb: FormBuilder,
     private snackBar: MatSnackBar,
      private productCommunicationService: ProductCommunicationService ,
      private customerservice : CustomerService , 
      private productserveice : ProductCommunicationService) {

  }
  @Input() isAdminDashboard: boolean = false;

  @Input() product: any;


  navigate(path: any) {


    this.router.navigate([path]);

  }



  deleteProduct(productId: any) {
    this.adminservice.deleteProduct(productId).subscribe((res) => {
      if (res?.body == null) {
        this.snackBar.open('Product deleted successfully !!', 'Close', {
          duration: 5000,
        });
        this.productCommunicationService.notifyProductDeleted();
      } else {
        this.snackBar.open(res.message, 'Close', {
          duration: 5000,
          panelClass: 'error-snackbar',
        });
      }
    });
  }



  addToCard(id: any) {

   
      this.snackBar.open('Product added to cart successfully !!', 'Close', {
        duration: 5000,})
    

    
    
  
  }


}
