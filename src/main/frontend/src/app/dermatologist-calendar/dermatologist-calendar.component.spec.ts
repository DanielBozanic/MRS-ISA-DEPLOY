import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistCalendarComponent } from './dermatologist-calendar.component';

describe('DermatologistCalendarComponent', () => {
  let component: DermatologistCalendarComponent;
  let fixture: ComponentFixture<DermatologistCalendarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistCalendarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});