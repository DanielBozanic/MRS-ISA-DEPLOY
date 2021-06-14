import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistHomePageComponent } from './pharmacist-home-page.component';

describe('PharmacistHomePageComponent', () => {
  let component: PharmacistHomePageComponent;
  let fixture: ComponentFixture<PharmacistHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
