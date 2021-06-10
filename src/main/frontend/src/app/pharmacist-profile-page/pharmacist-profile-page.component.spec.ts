import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistProfilePageComponent } from './pharmacist-profile-page.component';

describe('PharmacistProfilePageComponent', () => {
  let component: PharmacistProfilePageComponent;
  let fixture: ComponentFixture<PharmacistProfilePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistProfilePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistProfilePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
