import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangePatientDataComponent } from './change-patient-data.component';

describe('ChangePatientDataComponent', () => {
  let component: ChangePatientDataComponent;
  let fixture: ComponentFixture<ChangePatientDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangePatientDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangePatientDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
