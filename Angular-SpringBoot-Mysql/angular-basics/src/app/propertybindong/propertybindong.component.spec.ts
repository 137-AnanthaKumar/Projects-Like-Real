import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertybindongComponent } from './propertybindong.component';

describe('PropertybindongComponent', () => {
  let component: PropertybindongComponent;
  let fixture: ComponentFixture<PropertybindongComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PropertybindongComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PropertybindongComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
