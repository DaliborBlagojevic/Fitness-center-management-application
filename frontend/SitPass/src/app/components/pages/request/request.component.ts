import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AccountRequest } from '../../../models/AccountRequest';
import { FormsModule, NgForm } from '@angular/forms';
import { RequestStatus } from '../../../models/RequestStatus';

@Component({
  selector: 'app-request',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './request.component.html',
  styleUrl: './request.component.css'
})
export class RequestComponent implements OnInit{

  listOfRequests: AccountRequest[] = []
  request!: AccountRequest
  showModal: boolean = false;

  constructor(private router: Router, private http:HttpClient) {}

  ngOnInit(): void {
    this.getRequests();
  }

  getRequests() {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
      'Content-Type': 'application/json'
    });
    
    this.http.get<AccountRequest[]> ('http://localhost:8080/SitPass/api/auth/requests', {headers}).subscribe (
      response => {
        this.listOfRequests = response;
      }
    )
  }

  public get getRequestTypes(): typeof RequestStatus {
    return RequestStatus; 
  }


  handleRequest(request: AccountRequest, type: RequestStatus) {
    console.log(request)
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
      'Content-Type': 'application/json'
    });
    request.requestStatus = type;
    this.http.put<AccountRequest> (`http://localhost:8080/SitPass/api/auth/requests/${request.id}`, request ,{headers}).subscribe(
      response => {
        this.request = response;
        console.log(this.request)
        this.getRequests();
      }
    )
  }
}
