import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Facility } from '../../../models/Facility';
import { CommonModule } from '@angular/common';
import { FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule , Validators} from '@angular/forms';
import { Discipline } from '../../../models/Discipline';

@Component({
  selector: 'app-facility',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './facility.component.html',
  styleUrl: './facility.component.css'
})

export class FacilityComponent implements OnInit{

  listOfFacilities: Facility[] = []
  listOfDiscipline: Discipline[] = []
  facility!: Facility | null;
  searchForm!: FormGroup;
  selectedCity!: String
  selectedDiscipline!: String
  selectedDay!: String


  showDropdown: boolean = false;
  showModal: boolean = false;
  showDisciplineForm: boolean = false;
  showDaysForm: boolean = false;
  details: boolean = false;
  isActive: boolean | undefined = true;

  constructor(private http: HttpClient, private router:Router, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.getFacilities();
    this.getDisciplines()
    this.searchForm = this.fb.group({
      city: [''],  // Inicijalizacija polja
      discipline: [''],
      time: [''],
      minRate: [''],
      maxRate: ['']
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

    public toggleDetails(facilityId?: number): void {
      this.router.navigate(['/facility', facilityId]);
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

getDisciplines() {

      
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });
  this.http.get<Discipline[]>('http://localhost:8080/SitPass/api/disciplines', {headers}).subscribe(
    response => {

      this.listOfDiscipline = response;
      }
  )
}

searchFacilities() {
  const formData = this.searchForm.value;
  let path = "";
  // Kreiranje query parametara na osnovu vrednosti iz forme
  let params = new HttpParams();

  if (formData.city) {
    params = params.set('city',  formData.city);
    path = "city";  }
  if (formData.discipline) {
    params = params.set('discipline', formData.discipline);
    path = "discipline";
  }
  if (formData.time) {
    params = params.set('time', formData.time);
    path = "day";
  }
  if (formData.minRate) {
    params = params.set('minRate', formData.minRate);
    path = "rate";
  }
  if (formData.maxRate) {
    params = params.set('maxRate', formData.maxRate);
    path = "rate";
  }

  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });
  console.log(formData)
console.log(path)
  this.http.get<Facility[]>(`http://localhost:8080/SitPass/api/facilities/search/${path}`, { headers, params }).subscribe(
    response => {
      this.listOfFacilities = response;
      console.log(response);
    }
  );
}


selectDay(day: String){
  this.selectedDay = day
}


}