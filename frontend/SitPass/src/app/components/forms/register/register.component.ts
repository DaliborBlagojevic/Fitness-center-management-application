import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../../models/User';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  errorMsg: string = '';

  constructor(private http: HttpClient, private router:Router) {}

  onSubmit(form: NgForm) {
    if (form.valid) {
      const loginData = form.value;
      console.log(form.value)
      this.http.post<any>('http://localhost:8080/SitPass/api/auth', loginData).subscribe(
        response => {
          form.reset();
        },
        error => {
          this.errorMsg = 'Register error';
        }
      );
    }
  }

  public loginForm(): void {
    this.router.navigate(['/login']);
  }

}
