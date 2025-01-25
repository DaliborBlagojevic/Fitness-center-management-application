import { Component } from '@angular/core';
import { RequestComponent } from "../request/request.component";
import { FacilityAdminComponent } from "../facilitiy-admin/facilitiy-admin.component";
import { UserComponent } from "../user/user.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [RequestComponent, FacilityAdminComponent, UserComponent, CommonModule],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {

  showFacility: boolean = false;
  showUser: boolean = false;
  showRequest: boolean = false;

  toggleFacility(): void {
    this.showFacility = !this.showFacility;
  }

  toggleUser(): void {
    this.showUser = !this.showUser;
  }

  toggleRequest(): void {
    this.showRequest = !this.showRequest;
  }


}
