import { Component } from '@angular/core';
import { CustomerService } from '../../service/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.scss']
})
export class MyOrdersComponent {

  myOrders: any;

constructor(private customerService: CustomerService , private router : Router) {}

ngOnInit() {
  this.getMyOrders();
}

getMyOrders() {
  this.customerService.getOrderByUserId().subscribe(res => {
    this.myOrders = res;
  });
}




}
