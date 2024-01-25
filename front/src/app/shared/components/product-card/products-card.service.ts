// product-communication.service.ts
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { UserStorageService } from 'src/app/services/Storage/user-storage.service';



const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class ProductCommunicationService {


  constructor(private http: HttpClient) { }

  
  private productDeletedSource = new Subject<void>();

  productDeleted$ = this.productDeletedSource.asObservable();

  notifyProductDeleted() {
    this.productDeletedSource.next();
  }



  addToCart(productId: any): Observable<any> {

    const cartDto = {

      productId: productId,

      userId: UserStorageService.getUserId()

    }

    return this.http.post(BASIC_URL + 'api/customer/cart' ,cartDto , {
      headers: this.createAuthorizationHeader(),
    })
  }


  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set('Authorization', 'Bearer ' + UserStorageService.getToken());
  }
  


}
