import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewOrderedProductComponent } from './view-ordered-product.component';

describe('ViewOrderedProductComponent', () => {
  let component: ViewOrderedProductComponent;
  let fixture: ComponentFixture<ViewOrderedProductComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewOrderedProductComponent]
    });
    fixture = TestBed.createComponent(ViewOrderedProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
