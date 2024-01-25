import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-post-product',
  templateUrl: './post-product.component.html',
  styleUrls: ['./post-product.component.scss']
})
export class PostProductComponent {


  productForm: FormGroup;
  listofCategories: any = [];
  imagePreview: string | ArrayBuffer | null;
  selectedFile: File | null;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService
  ) { }


  onFileSelected(event: any) {

    this.selectedFile = event.target.files[0];

    this.previewImage();
  }

  previewImage() {
    const reader = new FileReader();

    reader.onload = () => {

      this.imagePreview = reader.result;

    }

    reader.readAsDataURL(this.selectedFile);
  }



  ngOnInit(): void {

    this.productForm = this.fb.group({
      categoryId: [null, [Validators.required]],
      name: [null, [Validators.required]],
      price: [null, [Validators.required]],
      description: [null, [Validators.required]],

    });

    this.getAllcategories();
  }



  getAllcategories() {

    this.adminService.getAllCategory().subscribe(res => {
      this.listofCategories = res;
    })

  }



  addProduct(): void {


    if (this.productForm.valid) {

      const formdata: FormData = new FormData();

      formdata.append('img', this.selectedFile);
      formdata.append('categoryId', this.productForm.get('categoryId').value);
      formdata.append('name', this.productForm.get('name').value);
      formdata.append('description', this.productForm.get('description').value);
      formdata.append('price', this.productForm.get('price').value);

      this.adminService.addProduct(formdata).subscribe((res) => {
        if (res.id != null) {
          this.snackBar.open('Product Posted Successfully!', 'Close', {
            duration: 5000,
          });
          this.router.navigateByUrl('/admin/dashboard');
        } else {
          this.snackBar.open(res.message, 'ERROR', {
            duration: 5000,
          });
        }
      })
    }
    else {
      for (const i in this.productForm.controls) {
        this.productForm.controls[i].markAsDirty;
        this.productForm.controls[i].updateValueAndValidity;

      }
    }


  }




}
