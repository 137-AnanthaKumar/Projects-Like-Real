import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyaccontComponent } from './myaccont.component';

describe('MyaccontComponent', () => {
  let component: MyaccontComponent;
  let fixture: ComponentFixture<MyaccontComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyaccontComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MyaccontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
