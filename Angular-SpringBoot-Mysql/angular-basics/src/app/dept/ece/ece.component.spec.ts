import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EceComponent } from './ece.component';

describe('EceComponent', () => {
  let component: EceComponent;
  let fixture: ComponentFixture<EceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
