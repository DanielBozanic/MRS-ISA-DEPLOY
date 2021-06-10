import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicinePrescriptionComponent } from './medicine-prescription.component';

describe('MedicinePrescriptionComponent', () => {
  let component: MedicinePrescriptionComponent;
  let fixture: ComponentFixture<MedicinePrescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicinePrescriptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicinePrescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
