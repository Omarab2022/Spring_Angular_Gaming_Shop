import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.scss']
})
export class CouponsComponent {

  coupons : any ;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.getCoupons();
  }

  getCoupons(): void {
    this.adminService.getCoupons().subscribe(
      (res) => {
        this.coupons = res; // Assuming that your response is an array, adjust accordingly
      },
      (error) => {
        console.error(error); // Log the error for debugging
      }
    );
  }

}
