import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../service/customer.service';
import { MatDialog } from '@angular/material/dialog';
import { PlaceorderComponent } from '../placeorder/placeorder.component';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent {


  cartItems : any[] ;

  order: any = {
    totalAmount: 0,
    amount: 0,
  };

  coupons : any[] ;

  couponForm!: FormGroup ; 

  appliedCoupon: any;

  constructor(private customerservice: CustomerService,
    private fb: FormBuilder ,  private snackBar: MatSnackBar,public dialog :MatDialog) {

  }


  ngOnInit(){
    
    this.couponForm = this.fb.group({
      code : [null , [Validators.required]]
    })

    this.cartItems 
     = [
      {
        productName: 'Elden Ring',
        Img: 'https://i.pinimg.com/564x/50/3d/70/503d7005d6b5add847430b7e40220f57.jpg',
        price: 500,
        description : " invites players to embark on an epic journey through time. Dive into a visually stunning world, make impactful choices, and master time manipulation in strategic combat.  ",
        quantity: 1,
        highlighted: false,
      },
      {
        productName: 'Gta 5 ',
        Img: 'https://i.pinimg.com/564x/ce/98/1f/ce981f90db75685da4a2f90536a3fc06.jpg',
        price: 500,
        quantity: 2,
        description : " invites players to embark on an epic journey through time. Dive into a visually stunning world, make impactful choices, and master time manipulation in strategic combat.  ",
        highlighted: true,
      },
      
    ];

    // Calculate the initial total amount based on the items in the cart
  this.order.totalAmount = this.calculateTotalAmount();
  this.order.amount = this.order.totalAmount;


    this.coupons =[
      {
        code : 4444 , 
        discount : 20 , 
        name : "coupon1" , 
      
      },
      {
        code : 12345 , 
        discount : 30 , 
        name : "coupon2" ,
      },

    ]
    
  }

  calculateTotalAmount(): number {
    return this.cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
  }


  decreaseQuantity(item: any) {
    if (item.quantity > 1) {
      item.quantity--;
      this.updateTotalAmount();
    }
  }

  increaseQuantity(item: any) {
    item.quantity++;
    this.updateTotalAmount();
  }

  updateTotalAmount() {
    let totalAmount = this.cartItems.reduce((total, item) => total + item.price * item.quantity, 0);

    // Apply coupon discount if a coupon is applied
    if (this.appliedCoupon) {
      totalAmount -= (totalAmount * this.appliedCoupon.discount) / 100;
    }

    this.order.totalAmount = totalAmount;
    this.order.amount = this.order.totalAmount;
  }



  applyCoupon() {
    const enteredCode = +this.couponForm.get('code')?.value; // Convert to number
  
    // Find the coupon with the entered code
    const coupon = this.coupons.find(c => c.code === enteredCode);
  
    if (coupon) {
      // Apply the coupon
      this.appliedCoupon = coupon;
      this.updateTotalAmount();
      this.snackBar.open(`Coupon '${coupon.name}' applied successfully!`, 'Close', { duration: 2000 });
    } else {
      this.snackBar.open('Invalid coupon code', 'Close', { duration: 2000 });
    }
  }
  
  

  placeOrder(){
    this.dialog.open(PlaceorderComponent);
  }


}
