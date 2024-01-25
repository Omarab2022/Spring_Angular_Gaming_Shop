import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../service/customer.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {


  products: any[] = [];


  searchproductForm!: FormGroup;



  constructor(private customerservice: CustomerService,
    private fb: FormBuilder ,  private snackBar: MatSnackBar,) {

  }


  ngOnInit() {

    this.getAllproducts();
    this.searchproductForm = this.fb.group({
      title: [null, [Validators.required]]
    });

    
  }


  getAllproducts() {
    this.products = [];


    this.customerservice.getAllProducts().subscribe(res => {

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
    this.customerservice.getAllProductsByName(title).subscribe(res => {

      res.forEach(element => {

        element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
        console.log(element.processedImg);
        this.products.push(element);

      });
    })

  }





}
