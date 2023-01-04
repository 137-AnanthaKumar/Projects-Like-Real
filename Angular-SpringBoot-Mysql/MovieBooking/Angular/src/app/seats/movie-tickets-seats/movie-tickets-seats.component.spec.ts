import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieTicketsSeatsComponent } from './movie-tickets-seats.component';

describe('MovieTicketsSeatsComponent', () => {
  let component: MovieTicketsSeatsComponent;
  let fixture: ComponentFixture<MovieTicketsSeatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MovieTicketsSeatsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovieTicketsSeatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
