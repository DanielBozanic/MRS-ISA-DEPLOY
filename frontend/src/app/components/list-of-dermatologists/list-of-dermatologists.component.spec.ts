import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfDermatologistsComponent } from './list-of-dermatologists.component';

describe('ListOfDermatologistsComponent', () => {
  let component: ListOfDermatologistsComponent;
  let fixture: ComponentFixture<ListOfDermatologistsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListOfDermatologistsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOfDermatologistsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
