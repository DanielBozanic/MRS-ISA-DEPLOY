import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeclineAbsenceRequestComponent } from './decline-absence-request.component';

describe('DeclineAbsenceRequestComponent', () => {
  let component: DeclineAbsenceRequestComponent;
  let fixture: ComponentFixture<DeclineAbsenceRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeclineAbsenceRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeclineAbsenceRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
