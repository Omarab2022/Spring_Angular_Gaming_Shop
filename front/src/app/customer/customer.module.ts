import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerComponent } from './customer.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { demoangularmaterialmodel } from '../demoangularmaterialmodele';
import { SharedModule } from '../shared/shared.module';
import { CartComponent } from './Components/cart/cart.component';
import { PlaceorderComponent } from './Components/placeorder/placeorder.component';
import { MyOrdersComponent } from './Components/my-orders/my-orders.component';
import { ViewOrderedProductComponent } from './Components/view-ordered-product/view-ordered-product.component';
import { ReviewOrderProductComponent } from './Components/review-order-product/review-order-product.component';
import { ViewProductDetailsComponent } from './Components/view-product-details/view-product-details.component';
import { ViewWishListComponent } from './Components/view-wish-list/view-wish-list.component';


@NgModule({
  declarations: [
    CustomerComponent,
    DashboardComponent,
    CartComponent,
    PlaceorderComponent,
    MyOrdersComponent,
    ViewOrderedProductComponent,
    ReviewOrderProductComponent,
    ViewProductDetailsComponent,
    ViewWishListComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    demoangularmaterialmodel,
    SharedModule
  ]
})
export class CustomerModule { }
