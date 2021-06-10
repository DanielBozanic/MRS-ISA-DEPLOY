import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistChoosePredefinedComponent } from './dermatologist-choose-predefined.component';

describe('DermatologistChoosePredefinedComponent', () => {
  let component: DermatologistChoosePredefinedComponent;
  let fixture: ComponentFixture<DermatologistChoosePredefinedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistChoosePredefinedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistChoosePredefinedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});