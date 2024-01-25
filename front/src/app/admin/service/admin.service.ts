import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/services/Storage/user-storage.service';



const BASIC_URL="http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http : HttpClient  ) { }


  addCategory(CategoryDto: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/admin/category', CategoryDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  getAllCategory(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin', {
      headers: this.createAuthorizationHeader(),
    })
  }


  addProduct(ProductDto: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/admin/product', ProductDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  UpdateProduct(productId:any , ProductDto: any): Observable<any> {
    return this.http.put(BASIC_URL + `api/admin/product/${productId}`, ProductDto, {
      headers: this.createAuthorizationHeader(),
    })
  }


  getAllProducts(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/products', {
      headers: this.createAuthorizationHeader(),
    })
  }


  getAllProductsByName(name:any): Observable<any> {
    return this.http.get(BASIC_URL + `api/admin/search/${name}`, {
      headers: this.createAuthorizationHeader(),
    })
  }
  

  deleteProduct(ProductId: any): Observable<any> {
    return this.http.delete(BASIC_URL + `api/admin/product/${ProductId}`, {
      headers: this.createAuthorizationHeader(),
    })
  }
  
  addCoupon(CouponDto: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/admin/coupons', CouponDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

    
  getCoupons(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/coupons', {
      headers: this.createAuthorizationHeader(),
    })
  }


  getPlacedOrders(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/placedOrders', {
      headers: this.createAuthorizationHeader(),
    })
  }

  getAnalytics(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/order/analytics', {
      headers: this.createAuthorizationHeader(),
    })
  }

  ChengeOrderStatus(orderId : number , status : string): Observable<any> {
    return this.http.get(BASIC_URL + `api/admin/order/${orderId}/${status}`, {
      headers: this.createAuthorizationHeader(),
    })
  }

  postFAQ(productId : number , FAQdto : any): Observable<any> {
    return this.http.post(BASIC_URL + `api/admin/faq/${productId}`, FAQdto, {
      headers: this.createAuthorizationHeader(),
    })
  }



  getProductById(productId): Observable<any> {
    return this.http.get(BASIC_URL + `api/admin/product/${productId}`, {
      headers: this.createAuthorizationHeader(),
    })
  }

  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set('Authorization', 'Bearer ' + UserStorageService.getToken())
  }
  
}
