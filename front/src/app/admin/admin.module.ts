import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { demoangularmaterialmodel } from '../demoangularmaterialmodele';
import { PostCategoryComponent } from './Components/post-category/post-category.component';
import { PostProductComponent } from './Components/post-product/post-product.component';
import { SharedModule } from '../shared/shared.module';
import { PostCouponComponent } from './Components/post-coupon/post-coupon.component';
import { CouponsComponent } from './Components/coupons/coupons.component';
import { OrdersComponent } from './Components/orders/orders.component';
import { PostFAQComponent } from './Components/post-faq/post-faq.component';
import { UpdateproductComponent } from './Components/updateproduct/updateproduct.component';
import { AnalysticsComponent } from './Components/analystics/analystics.component';
import { OrderBystatusComponent } from './Components/analystics/order-bystatus/order-bystatus.component';


@NgModule({
  declarations: [
    AdminComponent,
    DashboardComponent,
    PostCategoryComponent,
    PostProductComponent,
    PostCouponComponent,
    CouponsComponent,
    OrdersComponent,
    PostFAQComponent,
    UpdateproductComponent,
    AnalysticsComponent,
    OrderBystatusComponent,
    
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    demoangularmaterialmodel,
    SharedModule
    
  ]
})
export class AdminModule { }
