import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientsComplaintsDataComponent } from './patients-complaints-data.component';

describe('PatientsComplaintsDataComponent', () => {
  let component: PatientsComplaintsDataComponent;
  let fixture: ComponentFixture<PatientsComplaintsDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientsComplaintsDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientsComplaintsDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
