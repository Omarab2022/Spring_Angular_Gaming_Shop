import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent {

  orders: any;

  constructor(private adminService: AdminService, private snackBar: MatSnackBar) {}

  ngOnInit() {
    this.getPlacedOrders();
  }

  getPlacedOrders() {
    this.adminService.getPlacedOrders().subscribe(
      (res) => {
        this.orders = res;
      },
      (error) => {
        console.error('Error fetching placed orders:', error);
        this.snackBar.open('Error fetching placed orders', 'Close', { duration: 5000 });
      }
    );
  }

  changeOrderStatus(order: any, status: string) {
    // Update the status of the selected order
    order.Orderstatus = status;

    // Display a snackbar message
    this.snackBar.open(`Order status changed to ${status}`, 'Close', { duration: 2000 });
  }
  

}
