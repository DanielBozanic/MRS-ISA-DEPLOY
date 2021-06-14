import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyAdminComponent } from './pharmacy-admin.component';

describe('PharmacyAdminComponent', () => {
  let component: PharmacyAdminComponent;
  let fixture: ComponentFixture<PharmacyAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacyAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
