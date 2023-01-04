import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainsearchComponent } from './trainsearch.component';

describe('TrainsearchComponent', () => {
  let component: TrainsearchComponent;
  let fixture: ComponentFixture<TrainsearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrainsearchComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrainsearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
