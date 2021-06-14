import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistReservationsComponent } from './pharmacist-reservations.component';

describe('PharmacistReservationsComponent ', () => {
  let component: PharmacistReservationsComponent ;
  let fixture: ComponentFixture<PharmacistReservationsComponent >;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistReservationsComponent  ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});