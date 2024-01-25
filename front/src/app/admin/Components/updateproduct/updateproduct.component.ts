import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-updateproduct',
  templateUrl: './updateproduct.component.html',
  styleUrls: ['./updateproduct.component.scss']
})
export class UpdateproductComponent {


  productId = this.activateroute.snapshot.params['productId']

  productForm: FormGroup;
  listofCategories: any = [];
  imagePreview: string | ArrayBuffer | null;
  selectedFile: File | null;

  existingimage : string | null = null;
  imgChanged = false ;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService,
    private activateroute : ActivatedRoute
  ) { }


  onFileSelected(event: any) {

    this.selectedFile = event.target.files[0];

    this.previewImage();

    this.imgChanged = true ;

  

 
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
    this.getProductById();
  }



  getAllcategories() {

    this.adminService.getAllCategory().subscribe(res => {
      this.listofCategories = res;

    })

  }

  getProductById() {
    this.adminService.getProductById(this.productId).subscribe(
      (res) => {
        this.productForm.patchValue(res);
        this.existingimage = 'data:image/jpeg;base64,' + res.byteImg;
      },
      (error) => {
        // Handle error, e.g., show an error message
        console.error('Error fetching product details:', error);
      }
    );
  }
  



  updateProduct(): void {


    if (this.productForm.valid) {

      const formdata: FormData = new FormData();

      if (this.imgChanged && this.selectedFile) {
        formdata.append('img', this.selectedFile);
      }

     
      formdata.append('categoryId', this.productForm.get('categoryId').value);
      formdata.append('name', this.productForm.get('name').value);
      formdata.append('description', this.productForm.get('description').value);
      formdata.append('price', this.productForm.get('price').value);

      this.adminService.UpdateProduct(this.productId , formdata).subscribe((res) => {
        if (res.id != null) {
          this.snackBar.open('Product updated Successfully!', 'Close', {
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
