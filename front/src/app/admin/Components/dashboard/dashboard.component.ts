import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProductCommunicationService } from 'src/app/shared/components/product-card/products-card.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {



  products: any[] = [];


  searchproductForm!: FormGroup;


  

  constructor(private adminservice: AdminService,
    private fb: FormBuilder ,  private snackBar: MatSnackBar,private productCommunicationService: ProductCommunicationService) {

  }


  ngOnInit() {

    this.getAllproducts();
    this.searchproductForm = this.fb.group({
      title: [null, [Validators.required]]
    });

    this.productCommunicationService.productDeleted$.subscribe(() => {
      // Refresh the product list or take appropriate action
      this.getAllproducts();
    });
  }


  getAllproducts() {
    this.products = [];


    this.adminservice.getAllProducts().subscribe(res => {

      res.forEach(element => {

        element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
        console.log(element.processedImg);
        this.products.push(element);

      });
    })
  }


  submitForm() {


    this.products = [];

    const title = this.searchproductForm.get('title')!.value;
    this.adminservice.getAllProductsByName(title).subscribe(res => {

      res.forEach(element => {

        element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
        console.log(element.processedImg);
        this.products.push(element);

      });
    })

  }






}
