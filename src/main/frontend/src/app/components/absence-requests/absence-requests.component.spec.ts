import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbsenceRequestsComponent } from './absence-requests.component';

describe('AbsenceRequestsComponent', () => {
  let component: AbsenceRequestsComponent;
  let fixture: ComponentFixture<AbsenceRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AbsenceRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AbsenceRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
