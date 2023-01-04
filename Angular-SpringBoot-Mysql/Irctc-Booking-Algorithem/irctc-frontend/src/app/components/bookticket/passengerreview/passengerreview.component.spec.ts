import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PassengerreviewComponent } from './passengerreview.component';

describe('PassengerreviewComponent', () => {
  let component: PassengerreviewComponent;
  let fixture: ComponentFixture<PassengerreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PassengerreviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PassengerreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
