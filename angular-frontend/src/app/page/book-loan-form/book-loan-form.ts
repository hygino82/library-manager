import {Component, OnInit} from '@angular/core';
import {BookService} from '../../services/book.service';
import {UserService} from '../../services/user-service';
import {ActivatedRoute, Router} from '@angular/router';
import {BookLoanService} from '../../services/book-loan-service';

@Component({
  selector: 'app-book-loan-form',
  imports: [],
  templateUrl: './book-loan-form.html',
  styleUrl: './book-loan-form.css',
})
export class BookLoanForm implements OnInit {
  constructor(private readonly bookService: BookService,
              private readonly userService: UserService,
              private readonly bookLoanService: BookLoanService,
              private readonly router: Router,
              private readonly route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.bookId = params.get('bookId');
    });
  }

  userId: string | null = '';
  bookId: string | null = '';
  editMode = false;


  getBook(bookId: string) {
    this.bookService.getBook(bookId)
  }
}
