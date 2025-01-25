import { Component, NgModule, OnInit } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { User } from '../../models/User';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{
  showDropdown: boolean = false;
  email = localStorage.getItem('email');
  user!: User | null
[x: string]: any;
  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.getUser();
  }


  public logout(): void { 
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('email');
    this.user = null
    this.router.navigate(['/login']);
  }

  public toggleDropdown(): void {
    this.showDropdown = !this.showDropdown
  }

  getUser() {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
      'Content-Type': 'application/json'
    });
    this.http.get<User>(`http://localhost:8080/SitPass/api/users/${this.email}`, {headers}).subscribe(
      response => {
        this.user = response;
      }
    )
  }

  getRequests(): void {
    this.router.navigate(["/admin"])
  }

  getFacilities(): void {
    this.router.navigate(['/facilities'])
  }

  getProfile(): void {
    this.router.navigate(['/user'])
  }
}


