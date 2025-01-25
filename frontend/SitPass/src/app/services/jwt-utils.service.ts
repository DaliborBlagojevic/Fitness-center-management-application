import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JwtUtilsService {

  constructor() { }

  getRole(token: string) {
    const jwtData = token.split('.')[1];
    const decodedJwtJsonData = atob(jwtData);
    const decodedJwtData = JSON.parse(decodedJwtJsonData);
    return decodedJwtData.role;
  }

  getEmail(token: string) {
    const jwtData = token.split('.')[1];
    const decodedJwtJsonData = atob(jwtData);
    const decodedJwtData = JSON.parse(decodedJwtJsonData);
    return decodedJwtData.sub;
  }
}