import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, NgForm, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../../models/User';
import { zip } from 'rxjs';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{

  user!: User | null
  email = localStorage.getItem('email');
  showEditModal: boolean = false;
  showDataPicker: boolean = false;
  showEditPassword: boolean = false;

  userForm!: FormGroup;
  passwordForm!: FormGroup;

  errorMsg: string = "";

  constructor(private http: HttpClient, private router: Router, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.userForm = this.fb.group({
      name: ['', Validators.required],
      surname: [''],
      phoneNumber: [''],
      birthday: [''],
      city: [''],
      zipCode: [''],      
    });
    this.passwordForm = this.fb.group({
      password: [''],  
      validation: ['']   
    });


    this.getUser();
    console.log(this.user?.id)
  }

  toggleEditModal(): void {
    this.showEditModal = !this.showEditModal;
  }
  toggleEditPassword(): void {
    this.showEditPassword = !this.showEditPassword;
  }
  toggleDataPicker(): void {
    this.showDataPicker = !this.showDataPicker;
  }

  getUser() {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
      'Content-Type': 'application/json'
    });
    this.http.get<User>(`http://localhost:8080/SitPass/api/users/${this.email}`, {headers}).subscribe(
      response => {
        this.user = response;
        
        this.userForm.patchValue({
          name: response.name,
          surname: response.surname,
          phoneNumber: response.phoneNumber,
          city: response.city,
          zipCode: response.zipCode,
          birthday: response.birthday
        });
      }
    );
  }

  editPassword() {
    const formData = this.passwordForm.value;
    if (formData.password === formData.validation) {
        this.errorMsg = '';  // Clear error message if passwords match

        const headers = new HttpHeaders({
            'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
            'Content-Type': 'application/json'
        });

        this.http.patch<User>(`http://localhost:8080/SitPass/api/users/${this.user?.id}`, formData, { headers }).subscribe(
            response => {
                this.user = response;
                this.getUser(); // Refresh user after update
                console.log(response);
            },
            error => {
                this.errorMsg = 'Error updating password. Please try again!';
            }
        );
    } else {
        this.errorMsg = "Passwords do not match!";
    }
}


userDetails() {
  if (this.userForm.valid) {
    const formData = this.userForm.value;
    console.log(formData);
    
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
      'Content-Type': 'application/json'
    });

    this.http.put<User>(`http://localhost:8080/SitPass/api/users/${this.email}`, formData, { headers }).subscribe(
      response => {
        this.user = response;
        this.getUser(); // Call the function to refresh the user data
        console.log(response);
        this.errorMsg = ''; // Clear the error message on success
      },
      error => {
        this.errorMsg = 'Not valid'; // Set the error message on failure
      }
    );
  } else {
    this.errorMsg = 'Please fill in all required fields correctly.'; // Set error message if the form is invalid
  }
}

}
