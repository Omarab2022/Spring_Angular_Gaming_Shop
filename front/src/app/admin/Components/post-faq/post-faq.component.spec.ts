import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostFAQComponent } from './post-faq.component';

describe('PostFAQComponent', () => {
  let component: PostFAQComponent;
  let fixture: ComponentFixture<PostFAQComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostFAQComponent]
    });
    fixture = TestBed.createComponent(PostFAQComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
