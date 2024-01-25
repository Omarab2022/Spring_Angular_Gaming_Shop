import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../service/customer.service';

@Component({
  selector: 'app-view-ordered-product',
  templateUrl: './view-ordered-product.component.html',
  styleUrls: ['./view-ordered-product.component.scss']
})
export class ViewOrderedProductComponent {


  orderId: any = this.activatedRoute.snapshot.params['orderId'];
  orderedProductDetailsList: any[] = [];

  totalAmount: any;

  constructor(private activatedRoute: ActivatedRoute, private customerService: CustomerService) {}

  ngOnInit() {
    this.orderedProductDetailsList = []; // Clear the list
    this.getOrderedProductsDetailsByOrderId();
  }
  
  getOrderedProductsDetailsByOrderId() {
    this.customerService.getOrderedProducts(this.orderId).subscribe(
      (res) => {
        if (res && res.productDtoList) {
          res.productDtoList.forEach((element) => {
            element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
            this.orderedProductDetailsList.push(element);
          });
          this.totalAmount = res.orderAmount;
        } else {
          console.error('Invalid response structure:', res);
          // Handle the case when the response structure is not as expected
        }
      },
      (error) => {
        console.error('API error:', error);
        // Handle API error
      }
    );
  }
  
  
}


