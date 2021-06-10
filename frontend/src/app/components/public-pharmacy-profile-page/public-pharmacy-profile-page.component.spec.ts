import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicPharmacyProfilePageComponent } from './public-pharmacy-profile-page.component';

describe('PublicPharmacyProfilePageComponent', () => {
  let component: PublicPharmacyProfilePageComponent;
  let fixture: ComponentFixture<PublicPharmacyProfilePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PublicPharmacyProfilePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicPharmacyProfilePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
