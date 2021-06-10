import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyProfilePageComponent } from './pharmacy-profile-page.component';

describe('PharmacyProfilePageComponent', () => {
  let component: PharmacyProfilePageComponent;
  let fixture: ComponentFixture<PharmacyProfilePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacyProfilePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyProfilePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
