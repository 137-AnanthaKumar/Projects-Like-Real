import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketviewComponent } from './ticketview.component';

describe('TicketviewComponent', () => {
  let component: TicketviewComponent;
  let fixture: ComponentFixture<TicketviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TicketviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TicketviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
