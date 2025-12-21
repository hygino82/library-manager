import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookLoanList } from './book-loan-list';

describe('BookLoanList', () => {
  let component: BookLoanList;
  let fixture: ComponentFixture<BookLoanList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookLoanList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookLoanList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
