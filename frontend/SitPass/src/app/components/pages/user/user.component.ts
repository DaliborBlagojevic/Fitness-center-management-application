import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../../models/User';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit{
  

  user!: User | null
  email = localStorage.getItem('email');
  listOfUsers: User [] = [];

  constructor(private http: HttpClient, private router: Router, private fb: FormBuilder) {}
  ngOnInit(): void {
    this.getUsers()
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

  getUsers() {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
      'Content-Type': 'application/json'
    });
    this.http.get<User[]>(`http://localhost:8080/SitPass/api/users`, {headers}).subscribe(
      response => {
        this.listOfUsers = response;
        console.log(response)
        
      }
    )
  }

}
