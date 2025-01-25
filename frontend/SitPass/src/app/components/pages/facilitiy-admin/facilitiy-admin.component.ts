import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Facility } from '../../../models/Facility';
import { CommonModule } from '@angular/common';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule , Validators} from '@angular/forms';
import { Discipline } from '../../../models/Discipline';

@Component({
  selector: 'app-facilitiy-admin',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './facilitiy-admin.component.html',
  styleUrl: './facilitiy-admin.component.css'
})
export class FacilityAdminComponent implements OnInit{

  listOfFacilities: Facility[] = []
  listOfDisciplines: Discipline[] = []
  facility!: Facility | null;
  discipline!: Discipline | null;

  facilityForm!: FormGroup;
  editForm!: FormGroup;
  editDisciplineForm!: FormGroup;

  showDropdown: boolean = false;
  showModal: boolean = false;
  showDisciplineForm: boolean = false;
  showDaysForm: boolean = false;
  edit: boolean = false;
  editDiscipline: boolean = false;
  isActive: boolean | undefined = true;

  constructor(private http: HttpClient, private router:Router, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.getFacilities();
    
    
    this.facilityForm = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      address: [''],
      city: [''],
      disciplines: this.fb.array([this.createDiscipline()]),
      workDays: this.fb.array(this.daysOfWeek.map(() => this.createWorkDay()))
    });
  
    this.editForm = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      address: [''],
      city: [''],
      disciplines: this.fb.array([this.createDiscipline()]),
      workDays: this.fb.array(this.daysOfWeek.map(() => this.createWorkDay()))
    });
    this.editDisciplineForm = this.fb.group({
      name: ['', Validators.required],
    });
  }
  
  
    
    public toggleDropdown(): void {
      this.showDropdown = !this.showDropdown
    }
    public toggleModal(): void {
      this.showModal = !this.showModal;
    }
    public toggleDisciplineForm(): void {
      this.showDisciplineForm = !this.showDisciplineForm;
    }
    public toggleDaysForm(): void {
      this.showDaysForm = !this.showDaysForm;
    }

    public toggleEditForm(id?: number): void {
      if (id === undefined) {
        return;
      }
      
      this.loadFacility(id);  // Load facility data for editing
      this.edit = true;       // Show the edit form/modal
    }

    public toggleEditDisciplineForm(id?: number): void {
      if (id === undefined) {
        return;
      }
      
      this.loadDisciplines(id);
      console.log(this.loadDisciplines(id)) // Load facility data for editing
      this.editDiscipline = true;       // Show the edit form/modal
    }
    
    

  getFacilities() {

      
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
      'Content-Type': 'application/json'
    });
    this.http.get<Facility[]>('http://localhost:8080/SitPass/api/facilities', {headers}).subscribe(
      response => {

        this.listOfFacilities = response;
        }
      
    )
  
}

getDisciplines(id: number) {

  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });
  this.http.get<Discipline[]>(`http://localhost:8080/SitPass/api/disciplines/${id}`, {headers}).subscribe(
    response => {

      this.listOfDisciplines = response;
      }
    
  )

}

loadFacility(id: number): void {
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });

  this.http.get<Facility>(`http://localhost:8080/SitPass/api/facilities/${id}`, { headers }).subscribe(
    response => {
      this.facility = response;
      
      // Populate the edit form with the fetched facility data
      this.editForm.patchValue({
        name: response.name,
        address: response.address,
        city: response.city,
        description: response.description,
        workDays: response.workDays
      });
    }
  );
}

loadDisciplines(id: number): void {
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });

  this.http.get<Discipline>(`http://localhost:8080/SitPass/api/disciplines/${id}`, { headers }).subscribe(
    response => {
      this.discipline = response;
      console.log(this.discipline)
      // Populate the edit form with the fetched facility data
      this.editForm.patchValue({
        name: response.name,
        
      });
    }
  );
}


  get workDays(): FormArray {
    return this.facilityForm.get('workDays') as FormArray;
  }

  get images(): FormArray {
    return this.facilityForm.get('images') as FormArray;
  }

  get disciplines(): FormArray {
    return this.facilityForm.get('disciplines') as FormArray;
  }

  createDiscipline(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required]
    });
  }
  
  createWorkDay(): FormGroup {
    return this.fb.group({
      day: [null],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required]
    });
  }
  
  removeWorkDay(index: number): void {
    this.workDays.removeAt(index);
  }

  daysOfWeek = [
    { name: 'Monday', value: 0 },
    { name: 'Tuesday', value: 1 },
    { name: 'Wednesday', value: 2 },
    { name: 'Thursday', value: 3},
    { name: 'Friday', value: 4 },
    { name: 'Saturday', value: 5 },
    { name: 'Sunday', value: 6 }
  ];

  submitForm() {
      const formData = this.facilityForm.value;
      console.log(formData)
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
        'Content-Type': 'application/json'
      });
      this.http.post<Facility>('http://localhost:8080/SitPass/api/facilities', formData, {headers}).subscribe(
        response => {
          this.facility = response
          console.log(formData)
          this.facilityForm.reset()
          this.showModal = !this.showModal;
        }
      )
    }

    facilityEdit(id: number | undefined): void {
      if (id === undefined) {
        return;
      }
    
      const formData = this.editForm.value;
      console.log(formData);
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
        'Content-Type': 'application/json'
      });
    
      this.http.put<Facility>(`http://localhost:8080/SitPass/api/facilities/${id}`, formData, { headers }).subscribe(
        response => {
          this.facility = response;
          this.getFacilities(); // Refresh the list after editing
          this.edit = false;    // Hide the modal after editing
        }
      );
    }
    

    facilityDisciplinesEdit(id: number | undefined): void {
      if (id === undefined) {
        return;
      }
    
      const formData = this.editDisciplineForm.value;
      console.log(formData);
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
        'Content-Type': 'application/json'
      });
    
      this.http.put<Discipline>(`http://localhost:8080/SitPass/api/disciplines/${id}`, formData, { headers }).subscribe(
        response => {
          this.discipline = response;
          this.getFacilities(); // Refresh the list after editing
          this.editDiscipline = false;    // Hide the modal after editing
        }
      );
    }
    

    deleteFacility(id: number | undefined) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
        'Content-Type': 'application/json'
      });
      this.http.delete<Facility>(`http://localhost:8080/SitPass/api/facilities/${id}`, {headers}).subscribe(
        response => {
          this.getFacilities();
        }
      )
    }

    onDayChange(dayValue: number, isChecked: boolean, index: number): void {
      const workDaysArray = this.facilityForm.get('workDays') as FormArray;
    
      if (workDaysArray && workDaysArray.at(index)) {
        const selectedWorkDay = workDaysArray.at(index) as FormGroup;
    
        if (selectedWorkDay.get('day')) {
          selectedWorkDay.get('day')?.setValue(isChecked ? dayValue : null);
        }
      }
    }
    
  }