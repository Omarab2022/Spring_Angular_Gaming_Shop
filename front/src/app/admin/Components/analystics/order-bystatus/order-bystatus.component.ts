import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-order-bystatus',
  templateUrl: './order-bystatus.component.html',
  styleUrls: ['./order-bystatus.component.scss']
})
export class OrderBystatusComponent {


  @Input() data : any ;
}
