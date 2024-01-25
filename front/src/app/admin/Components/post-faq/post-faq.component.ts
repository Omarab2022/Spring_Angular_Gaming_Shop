import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-post-faq',
  templateUrl: './post-faq.component.html',
  styleUrls: ['./post-faq.component.scss']
})
export class PostFAQComponent {


  productId: number = this.activatedRoute.snapshot.params["productId"];
  FAQForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.FAQForm = this.fb.group({
      question: [null, [Validators.required]],
      answer: [null, [Validators.required]]
    });
  }

  postFAQ(){
    this.adminService.postFAQ(this.productId, this.FAQForm.value).subscribe(
      (res) => {
        if (res.id !== null) {
          this.snackBar.open('FAQ Posted Successfully!', 'Close', {
            duration: 5000
          });
          this.router.navigate(['/admin/dashboard']);
        } else {
          this.snackBar.open('Something went wrong', 'Close', {
            duration: 5000,
            panelClass: 'error-snackbar'
          });
        }
      },
      (error) => {
        // Handle error, e.g., show an error message
        this.snackBar.open('Error submitting FAQ', 'Close', {
          duration: 5000,
          panelClass: 'error-snackbar'
        });
      }
    );
    
  }
}
