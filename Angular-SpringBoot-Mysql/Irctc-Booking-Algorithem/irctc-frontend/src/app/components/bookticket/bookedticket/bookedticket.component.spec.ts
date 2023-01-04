import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookedticketComponent } from './bookedticket.component';

describe('BookedticketComponent', () => {
  let component: BookedticketComponent;
  let fixture: ComponentFixture<BookedticketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookedticketComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookedticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
