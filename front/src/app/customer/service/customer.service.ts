import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/services/Storage/user-storage.service';



const BASIC_URL = "http://localhost:8080/";


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }




  getAllProducts(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/customer/products', {
      headers: this.createAuthorizationHeader(),
    })
  }




  getAllProductsByName(name: any): Observable<any> {
    return this.http.get(BASIC_URL + `api/customer/search/${name}`, {
      headers: this.createAuthorizationHeader(),
    })
  }



  getCartByUserId(): Observable<any> {

    const userId = 5;

    return this.http.get(BASIC_URL + `api/customer/cart/${userId}` , {
      headers: this.createAuthorizationHeader(),
    })
  }


  applyCoupon(code:any): Observable<any> {

    const userId = 5;

    return this.http.get(BASIC_URL + `api/customer/coupon/${userId}/${code}` , {
      headers: this.createAuthorizationHeader(),
    })
  }


  placeOrder(orderDto:any): Observable<any> {

    orderDto.userId = 5;

    return this.http.post(BASIC_URL + `api/customer/placeOrder` , orderDto, {
      headers: this.createAuthorizationHeader(),
    })
  }


  getOrderByUserId(): Observable<any> {

  
    return this.http.get(BASIC_URL + `api/customer/myOrders/5` , {
      headers: this.createAuthorizationHeader(),
    })
  }


  getOrderedProducts(orderId : number ): Observable<any> {

    return this.http.get(BASIC_URL + `api/customer/ordered_products/${orderId}` , {
      headers: this.createAuthorizationHeader(),
    })
  }


  giveReview(reviewDto :any ): Observable<any> {

    return this.http.post(BASIC_URL + `api/customer/review` , reviewDto , {
      headers: this.createAuthorizationHeader(),
    })
  }

  addProductToWishList(WishListDto :any ): Observable<any> {

    return this.http.post(BASIC_URL + `api/customer/wishlist` , WishListDto , {
      headers: this.createAuthorizationHeader(),
    })
  }


  GetwishlistByuserId(): Observable<any> {

    const userId = 5 ;
    return this.http.get(BASIC_URL + `api/customer/wishlist/${userId}`  , {
      headers: this.createAuthorizationHeader(),
    })
  }


  getProductDetailsById(productId : number ): Observable<any> {

    return this.http.get(BASIC_URL + `api/customer/product/${productId}` , {
      headers: this.createAuthorizationHeader(),
    })
  }



  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set('Authorization', 'Bearer ' + UserStorageService.getToken())
  }




}
