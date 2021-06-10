import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatiensMedicinesComponent } from './patiens-medicines.component';

describe('PatiensMedicinesComponent', () => {
  let component: PatiensMedicinesComponent;
  let fixture: ComponentFixture<PatiensMedicinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatiensMedicinesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatiensMedicinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
