import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GraphicReserveComponent } from './graphic-reserve.component';

describe('GraphicReserveComponent', () => {
  let component: GraphicReserveComponent;
  let fixture: ComponentFixture<GraphicReserveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GraphicReserveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GraphicReserveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
