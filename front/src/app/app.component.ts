import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { UserStorageService } from './services/Storage/user-storage.service';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit {
  title = 'ecomm_angular';

  @ViewChild('contentContainer') contentContainer!: ElementRef;

  constructor(private router: Router) {}

  ngAfterViewInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Scroll to the top when the route changes
        this.contentContainer.nativeElement.scrollIntoView({ behavior: 'smooth' });
      }
    });
  }
  

  }
  

  

