import { CommonModule } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { AppRoutingModule } from "../app-routing.module";
import { demoangularmaterialmodel } from "../demoangularmaterialmodele";
import { FooterComponent } from "./components/footer/footer.component";
import { NavbarComponent } from "./components/navbar/navbar.component";
import { ProductCardComponent } from "./components/product-card/product-card.component";


@NgModule({
  declarations: [
    NavbarComponent,
    FooterComponent,
    ProductCardComponent,
   
  ],
  imports: [
    CommonModule,
    demoangularmaterialmodel,
    HttpClientModule,
    FormsModule, 
    ReactiveFormsModule ,
    
  ], exports:[
   NavbarComponent,
   FooterComponent,
   ProductCardComponent
   
  ]
})
export class SharedModule { }
