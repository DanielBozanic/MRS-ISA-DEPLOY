import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RespondToComplaintsComponent } from './respond-to-complaints.component';

describe('RespondToComplaintsComponent', () => {
  let component: RespondToComplaintsComponent;
  let fixture: ComponentFixture<RespondToComplaintsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RespondToComplaintsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RespondToComplaintsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
