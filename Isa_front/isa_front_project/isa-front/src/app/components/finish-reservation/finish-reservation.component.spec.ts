import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinishReservationComponent } from './finish-reservation.component';

describe('FinishReservationComponent', () => {
  let component: FinishReservationComponent;
  let fixture: ComponentFixture<FinishReservationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinishReservationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinishReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
