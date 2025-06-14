import { TestBed } from '@angular/core/testing';

import { RateCommentService } from './rate-comment.service';

describe('RateCommentService', () => {
  let service: RateCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RateCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
