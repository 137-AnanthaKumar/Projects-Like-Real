import { TestBed } from '@angular/core/testing';

import { TickedServiseService } from './ticked-servise.service';

describe('TickedServiseService', () => {
  let service: TickedServiseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TickedServiseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
