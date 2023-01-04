import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MechComponent } from './mech.component';

describe('MechComponent', () => {
  let component: MechComponent;
  let fixture: ComponentFixture<MechComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MechComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MechComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
