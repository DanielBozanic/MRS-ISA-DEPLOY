import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistUsersComponent } from './dermatologist-users.component';

describe('PharmacistUsersComponent ', () => {
  let component: DermatologistUsersComponent ;
  let fixture: ComponentFixture<DermatologistUsersComponent >;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistUsersComponent  ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});