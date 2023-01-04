import { TestBed } from '@angular/core/testing';

import { PnrenqService } from './pnrenq.service';

describe('PnrenqService', () => {
  let service: PnrenqService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PnrenqService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
