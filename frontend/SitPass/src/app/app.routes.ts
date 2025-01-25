import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { LayoutComponent } from './components/layout/layout.component';
import { FacilityComponent } from './components/pages/facility/facility.component';
import { RegisterComponent } from './components/forms/register/register.component';
import { LoginComponent } from './components/forms/login/login.component';
import { CommonModule } from '@angular/common';
import { RequestComponent } from './components/pages/request/request.component';
import { ProfileComponent } from './components/pages/profile/profile.component';
import { DetailsFacilityComponent } from './components/pages/details-facility/details-facility.component';
import { AdminComponent } from './components/pages/admin/admin.component';
import { UserOrAdminGuardService } from './services/user.guard';
import { AdminGuard } from './services/admin.guard';

export const routes: Routes = [

    {
        path: '',
        component: LayoutComponent,
        children: [
            {path: 'login', component: LoginComponent},
            {path: 'facilities', component: FacilityComponent, canActivate: [UserOrAdminGuardService]},
            {path: 'register', component: RegisterComponent},
            {path: 'requests', component: RequestComponent , canActivate: [AdminGuard]},
            {path: 'user', component: ProfileComponent, canActivate: [UserOrAdminGuardService]},
            { path: 'facility/:id', component: DetailsFacilityComponent, canActivate: [UserOrAdminGuardService]},
            {path: 'admin', component: AdminComponent, canActivate: [AdminGuard]}
        ]    
    }

];

@NgModule ({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule{}