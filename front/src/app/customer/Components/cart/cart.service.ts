// cart.service.ts
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  cartItems: any[] = [];
  order: any = {
    totalAmount: 0,
    amount: 0,
  };
  appliedCoupon: any;

  coupons : any[];
}
