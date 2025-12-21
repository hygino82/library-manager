import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookLoanForm } from './book-loan-form';

describe('BookLoanForm', () => {
  let component: BookLoanForm;
  let fixture: ComponentFixture<BookLoanForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookLoanForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookLoanForm);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
