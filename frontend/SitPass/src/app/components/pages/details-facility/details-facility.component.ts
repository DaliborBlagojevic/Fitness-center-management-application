import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Facility } from '../../../models/Facility';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Discipline } from '../../../models/Discipline';
import { Comment } from '../../../models/Comment';
import { CommonModule } from '@angular/common';
import { WorkDay } from '../../../models/WorkDay';
import { Exercise } from '../../../models/Exercise';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Review } from '../../../models/Review';
import { Rate } from '../../../models/Rate';
import { User } from '../../../models/User';


@Component({
  selector: 'app-details-facility',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './details-facility.component.html',
  styleUrl: './details-facility.component.css'
})
export class DetailsFacilityComponent implements OnInit{

  errorMsg: string = "";

  faclity!: Facility | null;
  exercise!: Exercise | null
  review!: Review | null;
  user!: User | null;
  reviewForm!: FormGroup;
  exerciseForm!: FormGroup;

  listOfdisciplines: Discipline[] = []
  listOfWorkDays: WorkDay[] = []
  listOfReviews: Review[] = []
  listOfRates: Rate[] = []
  listOfComments: Comment[] = []

  email = localStorage.getItem('email');
  role = localStorage.getItem('role');
 

  showDisciplines: boolean = false;
  showWorkDays: boolean = false;
  showExercise: boolean = false;
  showReviews: boolean = false;
  showAddReviews: boolean = false;

  constructor(private http: HttpClient, private route: ActivatedRoute, private fb:FormBuilder) {}

ngOnInit(): void {
  

  this.route.paramMap.pipe(
    switchMap(params => {
      const facilityId = params.get('id');  
      return this.getFacility(facilityId);
    })
  ).subscribe(response => {
    this.faclity = response;
    this.review = response;
    this.getUserByEmail()
    this.reviewForm = this.fb.group({
      rates: this.fb.array([this.createRate()]),
      comments: this.fb.array([this.createComment()])
    });
    this.exerciseForm = this.fb.group({
      fromDate:[''],
      untilDate:['']
    });
  });

  
  


}

toggleWorkDays(): void {
  this.showWorkDays = !this.showWorkDays;
  this.getWorkDays();
}

toggleDisciplines(): void {
  this.showDisciplines = !this.showDisciplines;
  this.getDisciplines();
}

toggleExercise(): void {
  this.showExercise = !this.showExercise;
}
toggleReviews(): void {
  this.showReviews = !this.showReviews;
  this.getReviews();
  
}
toggleAddReviews(): void {
  if (this.role == 'ROLE_ADMIN') {
  this.showAddReviews = !this.showAddReviews; 
  }
}



getFacility(id: string | null) {
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });

  return this.http.get<Facility>(`http://localhost:8080/SitPass/api/facilities/${id}`, { headers })
}

getDisciplines() {
  
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });
    this.http.get<Discipline[]>(`http://localhost:8080/SitPass/api/disciplines/${this.faclity?.id}`, {headers}).subscribe(
      response => {
        this.listOfdisciplines = response;
      }
    )

}

getWorkDays() {
  
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });
    this.http.get<WorkDay[]>(`http://localhost:8080/SitPass/api/days/${this.faclity?.id}`, {headers}).subscribe(
      response => {
        this.listOfWorkDays = response;
            }
    )

}

getReviews() {
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });

  this.http.get<Review[]>(`http://localhost:8080/SitPass/api/reviews/${this.faclity?.id}`, { headers }).subscribe(
    response => {
      this.listOfReviews = response;
      this.review = null;  // Reset the selected review when loading new data
      if (this.listOfReviews.length > 0) { // Optionally select the first review by default
      }
    }
  );
}

selectReview(event: Event): void {
  const selectedReviewId = (event.target as HTMLSelectElement).value;
  const selectedReview = this.listOfReviews.find(review => review.id === +selectedReviewId);
  if (selectedReview) {
    this.review = selectedReview;
    this.getRates();
    this.getComments();
  }
}


getRates() {
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });
    this.http.get<Rate[]>(`http://localhost:8080/SitPass/api/reviews/rates/${this.review?.id}`, {headers}).subscribe(
      response => {
        this.listOfRates = response;        
      }
    )

}

getComments() {
  
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });
    this.http.get<Comment[]>(`http://localhost:8080/SitPass/api/reviews/comments/${this.review?.id}`, {headers}).subscribe(
      response => {
        this.listOfComments = response;
        console.log(this.listOfComments)
      }
    )

}

createExercise() {
  const formData = this.exerciseForm.value;
  console.log(formData);
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });

  this.http.post<Exercise>(`http://localhost:8080/SitPass/api/exercises/${this.faclity?.id}/${this.email}`, formData, { headers }).subscribe(
    response => {
      this.exercise = response;
      this.errorMsg = ''; // Clear any previous error message
    },
    error => {
      if (error.status === 400 && error.error.message) {
        this.errorMsg = error.error.message; // Set the error message
      } else {
        this.errorMsg = 'An unexpected error occurred. Please try again later.';
      }
    }
  );
}


createReview() {
  const formData = this.reviewForm.value;
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });

  this.http.post<Review>(`http://localhost:8080/SitPass/api/reviews/${this.user?.id}/${this.faclity?.id}`, formData, { headers }).subscribe(
    response => {
      this.review = response;
      this.reviewForm.reset();
      this.errorMsg = ''; // Clear any previous error message
    },
    error => {
      if (error.status === 400 && error.error.message) {
        this.errorMsg = error.error.message; // Set the error message from the backend
      } else {
        this.errorMsg = 'An unexpected error occurred. Please try again later.';
      }
    }
  );
}



get comments(): FormArray {
  return this.reviewForm.get('comments') as FormArray;
}

get rates(): FormArray {
  return this.reviewForm.get('rates') as FormArray;
}

createComment(): FormGroup {
  return this.fb.group({
    text: ['', Validators.required]
  });
}



createRate(): FormGroup {
  return this.fb.group({
    equipment: ['',Validators.required],
    staff: ['',Validators.required],
    hygene: ['',Validators.required],
    space: ['',Validators.required]
  });
}

getUserByEmail() {
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
    'Content-Type': 'application/json'
  });

  this.http.get<User>(`http://localhost:8080/SitPass/api/users/${this.email}`, { headers}).subscribe (
    response => {
      this.user = response;
    }
  )

}

}
