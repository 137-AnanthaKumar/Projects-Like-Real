import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainlistComponent } from './trainlist.component';

describe('TrainlistComponent', () => {
  let component: TrainlistComponent;
  let fixture: ComponentFixture<TrainlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrainlistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrainlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
