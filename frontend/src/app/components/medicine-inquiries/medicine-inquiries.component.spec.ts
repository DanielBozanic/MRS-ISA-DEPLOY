import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicineInquiriesComponent } from './medicine-inquiries.component';

describe('MedicineInquiriesComponent', () => {
  let component: MedicineInquiriesComponent;
  let fixture: ComponentFixture<MedicineInquiriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicineInquiriesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicineInquiriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
