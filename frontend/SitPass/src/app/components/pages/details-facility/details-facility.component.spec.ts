import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsFacilityComponent } from './details-facility.component';

describe('DetailsFacilityComponent', () => {
  let component: DetailsFacilityComponent;
  let fixture: ComponentFixture<DetailsFacilityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsFacilityComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsFacilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
