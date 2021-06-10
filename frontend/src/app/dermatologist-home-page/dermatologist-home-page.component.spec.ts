import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistHomePageComponent } from './dermatologist-home-page.component';

describe('DermatologistHomePageComponent', () => {
  let component: DermatologistHomePageComponent;
  let fixture: ComponentFixture<DermatologistHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
