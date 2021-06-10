import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewDermatologistComponent } from './add-new-dermatologist.component';

describe('AddNewDermatologistComponent', () => {
  let component: AddNewDermatologistComponent;
  let fixture: ComponentFixture<AddNewDermatologistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddNewDermatologistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewDermatologistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
