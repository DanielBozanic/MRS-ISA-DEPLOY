import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfPharmacistsComponent } from './list-of-pharmacists.component';

describe('ListOfPharmacistsComponent', () => {
  let component: ListOfPharmacistsComponent;
  let fixture: ComponentFixture<ListOfPharmacistsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListOfPharmacistsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOfPharmacistsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
