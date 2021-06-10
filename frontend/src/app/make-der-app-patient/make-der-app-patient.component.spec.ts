import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeDerAppPatientComponent } from './make-der-app-patient.component';

describe('MakeDerAppPatientComponent', () => {
  let component: MakeDerAppPatientComponent;
  let fixture: ComponentFixture<MakeDerAppPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakeDerAppPatientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeDerAppPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
