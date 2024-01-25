import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { PostCategoryComponent } from './Components/post-category/post-category.component';
import { PostProductComponent } from './Components/post-product/post-product.component';
import { PostCouponComponent } from './Components/post-coupon/post-coupon.component';
import { CouponsComponent } from './Components/coupons/coupons.component';
import { OrdersComponent } from './Components/orders/orders.component';
import { PostFAQComponent } from './Components/post-faq/post-faq.component';
import { UpdateproductComponent } from './Components/updateproduct/updateproduct.component';
import { AnalysticsComponent } from './Components/analystics/analystics.component';

const routes: Routes = [{ 
  path: '', component: AdminComponent },
{ path : 'dashboard' , component :DashboardComponent},
{path : 'category' , component : PostCategoryComponent},
{path :'product' , component : PostProductComponent} ,
{path:'product/:productId' , component : UpdateproductComponent} ,
{path : 'post-coupon' , component : PostCouponComponent},
{path:'coupons' , component : CouponsComponent} ,
{path:'orders' , component : OrdersComponent} , 
{path:'faq/:productId' , component : PostFAQComponent} ,
{path:'analytics' , component : AnalysticsComponent} 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
