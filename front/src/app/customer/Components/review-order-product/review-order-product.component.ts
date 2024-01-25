import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../service/customer.service';
import { UserStorageService } from 'src/app/services/Storage/user-storage.service';

@Component({
  selector: 'app-review-order-product',
  templateUrl: './review-order-product.component.html',
  styleUrls: ['./review-order-product.component.scss']
})
export class ReviewOrderProductComponent {



  productId: number | undefined;
  reviewForm!: FormGroup;
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private customerService: CustomerService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    console.log('ngOnInit called');
    this.activatedRoute.params.subscribe(params => {
      this.productId = +params['productId']; // Use '+' to convert the parameter to a number
      console.log('Product ID in ngOnInit:', this.productId);
    });
  
    this.reviewForm = this.fb.group({
      rating: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });
  }

  

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];

    this.previewImage();
    
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result ;
    };
    reader.readAsDataURL(this.selectedFile);
  }
  
  submitForm() {
    if (!this.reviewForm) {
      console.error('Form is not defined.');
      return;
    }
  
    if (!this.productId) {
      console.error('Product ID is undefined or null.');
      return;
    }
  
    console.log('Product ID in submitForm:', this.productId);
  
    const userId = 5; // Use 5 if userId is undefined or null
  
    const formData: FormData = new FormData();
    formData.append('img', this.selectedFile);
    formData.append('productId', this.productId.toString());
    formData.append('userId', userId.toString());
    formData.append('rating', this.reviewForm.get('rating').value);
    formData.append('description', this.reviewForm.get('description').value);
  
    this.customerService.giveReview(formData).subscribe(res => {
      console.log('Response from giveReview:', res);
  
      if (res.id !== null) {
        this.snackBar.open('Review Posted Successfully!', 'Close', {
          duration: 5000
        });
        this.router.navigate(['/customer/my_orders']);
      } else {
        this.snackBar.open('Something went wrong', 'ERROR', {
          duration: 5000
        });
      }
    });
  }
  
}
