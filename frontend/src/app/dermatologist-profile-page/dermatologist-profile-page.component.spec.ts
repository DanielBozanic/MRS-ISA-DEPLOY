import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistProfilePageComponent } from './dermatologist-profile-page.component';

describe('DermatologistProfilePageComponent', () => {
  let component: DermatologistProfilePageComponent;
  let fixture: ComponentFixture<DermatologistProfilePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistProfilePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistProfilePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
