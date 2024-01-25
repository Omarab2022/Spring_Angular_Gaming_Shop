import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../service/customer.service';
import { UserStorageService } from 'src/app/services/Storage/user-storage.service';

@Component({
  selector: 'app-view-product-details',
  templateUrl: './view-product-details.component.html',
  styleUrls: ['./view-product-details.component.scss']
})
export class ViewProductDetailsComponent {


  productId: number = this.activatedRoute.snapshot.params["productId"];
product: any;
FAQs: any[] = [];
reviews: any[] = [];

constructor(private snackBar: MatSnackBar,
   private customerService: CustomerService, 
   private activatedRoute: ActivatedRoute) {}

ngOnInit() {
  this.getProductDetailById();
}


getProductDetailById() {
  this.customerService.getProductDetailsById(this.productId).subscribe(res => {
    this.product = res.productDto;
    this.product.processedImg = 'data:image/png;base64,' + res.productDto.byteImg;

    this.FAQs = res.faqDtoList;

   
    res.reviewDtoList.forEach((element) => {
      element.processedImg = 'data:image/png;base64,' + element.returnedImg;
      this.reviews.push(element);
    });
  });
}

addToWishlist() {
  const wishlistDto = {
    productId: this.productId,
    userId: 5 // Assuming this is a method to get the user id
  };

  this.customerService.addProductToWishList(wishlistDto).subscribe(
    (res) => {
      if (res.id !== null) {
        this.snackBar.open('Product added to wishlist successfully!', 'close', {
          duration: 5000
        });
      } else {
        this.snackBar.open('Already in Wishlist', 'ERROR', {
          duration: 5000
        });
      }
    },
    (error) => {
      console.error('Error adding product to wishlist:', error);
      this.snackBar.open('Something went wrong', 'ERROR', {
        duration: 5000
      });
    }
  );
}



}
