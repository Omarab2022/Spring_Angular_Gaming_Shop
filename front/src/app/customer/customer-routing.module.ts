import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './customer.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { CartComponent } from './Components/cart/cart.component';
import { MyOrdersComponent } from './Components/my-orders/my-orders.component';
import { ViewOrderedProductComponent } from './Components/view-ordered-product/view-ordered-product.component';
import { ReviewOrderProductComponent } from './Components/review-order-product/review-order-product.component';
import { ViewProductDetailsComponent } from './Components/view-product-details/view-product-details.component';
import { ViewWishListComponent } from './Components/view-wish-list/view-wish-list.component';

const routes: Routes = [{ path: '', component: CustomerComponent },
{path :'dashboard' , component : DashboardComponent} ,
{path:'cart' , component : CartComponent},
{path:'my_orders' , component : MyOrdersComponent} , 
{path:'ordered_products/:orderId' , component : ViewOrderedProductComponent},
{ path: 'review/:productId', component: ReviewOrderProductComponent },
{ path: 'product/:productId', component: ViewProductDetailsComponent },
{ path: 'wishlist', component: ViewWishListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
