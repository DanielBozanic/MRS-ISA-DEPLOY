import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterPharmaciesComponent } from './register-pharmacies.component';

describe('RegisterPharmaciesComponent', () => {
  let component: RegisterPharmaciesComponent;
  let fixture: ComponentFixture<RegisterPharmaciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterPharmaciesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterPharmaciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
