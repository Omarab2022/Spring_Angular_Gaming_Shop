import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderBystatusComponent } from './order-bystatus.component';

describe('OrderBystatusComponent', () => {
  let component: OrderBystatusComponent;
  let fixture: ComponentFixture<OrderBystatusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrderBystatusComponent]
    });
    fixture = TestBed.createComponent(OrderBystatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
