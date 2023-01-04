import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AttriputebindingComponent } from './attriputebinding.component';

describe('AttriputebindingComponent', () => {
  let component: AttriputebindingComponent;
  let fixture: ComponentFixture<AttriputebindingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AttriputebindingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AttriputebindingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
