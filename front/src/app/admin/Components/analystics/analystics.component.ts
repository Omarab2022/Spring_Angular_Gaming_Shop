import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-analystics',
  templateUrl: './analystics.component.html',
  styleUrls: ['./analystics.component.scss']
})
export class AnalysticsComponent {


  data : any;

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.adminService.getAnalytics().subscribe(
      res  => {
        console.log(res);
       this.data = res ;
      },
      (error) => {
        console.error(error);
        // Handle the error if needed
      }
    );
  }

}
