import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TiketsearchComponent } from './tiketsearch.component';

describe('TiketsearchComponent', () => {
  let component: TiketsearchComponent;
  let fixture: ComponentFixture<TiketsearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TiketsearchComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TiketsearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
