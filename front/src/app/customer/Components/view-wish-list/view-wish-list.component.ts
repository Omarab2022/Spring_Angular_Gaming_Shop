import { Component } from '@angular/core';
import { CustomerService } from '../../service/customer.service';

@Component({
  selector: 'app-view-wish-list',
  templateUrl: './view-wish-list.component.html',
  styleUrls: ['./view-wish-list.component.scss']
})
export class ViewWishListComponent {



products: any[] = [];

constructor(private customerService: CustomerService) {}

ngOnInit() {
  this.getWishlistByUserId();
}

getWishlistByUserId() {
  this.customerService.GetwishlistByuserId().subscribe(res => {
    res.forEach(element => {
      element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
      this.products.push(element);
    });
  });
}

}
