import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientsEreceptsComponent } from './patients-erecepts.component';

describe('PatientsEreceptsComponent', () => {
  let component: PatientsEreceptsComponent;
  let fixture: ComponentFixture<PatientsEreceptsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientsEreceptsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientsEreceptsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
