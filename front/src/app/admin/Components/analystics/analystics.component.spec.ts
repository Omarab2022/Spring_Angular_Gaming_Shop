import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalysticsComponent } from './analystics.component';

describe('AnalysticsComponent', () => {
  let component: AnalysticsComponent;
  let fixture: ComponentFixture<AnalysticsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AnalysticsComponent]
    });
    fixture = TestBed.createComponent(AnalysticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
