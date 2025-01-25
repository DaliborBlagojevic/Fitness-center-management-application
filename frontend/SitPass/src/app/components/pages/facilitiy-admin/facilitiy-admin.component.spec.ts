import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilitiyAdminComponent } from './facilitiy-admin.component';

describe('FacilitiyAdminComponent', () => {
  let component: FacilitiyAdminComponent;
  let fixture: ComponentFixture<FacilitiyAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FacilitiyAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacilitiyAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
