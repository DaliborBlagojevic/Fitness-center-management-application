 import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NgForm, FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtUtilsService } from '../../../services/jwt-utils.service';
import { NavbarComponent } from '../../navbar/navbar.component';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule], // Dodaj FormsModule ovde
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  errorMsg: string = '';

  constructor(private http: HttpClient, private router: Router, private jwtService: JwtUtilsService) {}

  onSubmit(form: NgForm) {
    if (form.valid) {
      const loginData = form.value;
      this.http.post<any>('http://localhost:8080/SitPass/api/auth/login', loginData).subscribe(
        response => {
          localStorage.setItem('accessToken', response.accessToken);
          localStorage.setItem('refreshToken', response.accessToken);
          localStorage.setItem('email', this.jwtService.getEmail(response.accessToken))
          localStorage.setItem('role', this.jwtService.getRole(response.accessToken))
          console.log(this.jwtService.getRole(response.accessToken))

          form.reset();
          const headers = new HttpHeaders({
            'Authorization': `Bearer ${response.accessToken}`,
            'Content-Type': 'application/json'
          });
          this.router.navigate(['/facilities']);
        },
        error => {
          this.errorMsg = "Invalid email or password!";
        }
      );
    }
    else {
      this.errorMsg = "Please fill in all fields correctly.";
  }
  }
  public registerForm(): void {
    this.router.navigate(['/register'])
  }

}
