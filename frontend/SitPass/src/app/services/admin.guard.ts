import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { JwtUtilsService } from './jwt-utils.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private jwtUtilService: JwtUtilsService, private router: Router) { }

  canActivate(): boolean {
    const token = localStorage.getItem('accessToken');
    if (token) {
      const role = this.jwtUtilService.getRole(token);
      if (role == 'ROLE_ADMIN') {
        return true;
      } else {
        this.router.navigate([('/error/no-permission')])
        return false;
      }
    } else {
      this.router.navigate([('/login')])
      return false;
    }
  }
}