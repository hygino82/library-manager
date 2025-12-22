import {Component, OnInit} from '@angular/core';
import {ResponseBookLoan} from '../../../custom.types';
import {BookLoanService} from '../../services/book-loan-service';
import {TableModule} from 'primeng/table';
import {Tag} from 'primeng/tag';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-book-loan-list',
  templateUrl: './book-loan-list.html',
  imports: [
    TableModule,
    Tag,
    DatePipe
  ],
  styleUrls: ['./book-loan-list.css']
})
export class BookLoanList implements OnInit {
  bookLoans: ResponseBookLoan[] = [];

  constructor(private bookLoanService: BookLoanService) { }

  ngOnInit(): void {
    this.bookLoanService.getLoans().subscribe((response) => {
      this.bookLoans = response.content;
      console.log(this.bookLoans);
    });
  }
}
