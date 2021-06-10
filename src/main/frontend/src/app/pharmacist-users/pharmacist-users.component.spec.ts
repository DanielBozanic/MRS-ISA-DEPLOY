import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistUsersComponent } from './pharmacist-users.component';

describe('PharmacistUsersComponent ', () => {
  let component: PharmacistUsersComponent ;
  let fixture: ComponentFixture<PharmacistUsersComponent >;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistUsersComponent  ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});