import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {BookLoanService} from '../../services/book-loan-service';
import {FormsModule} from '@angular/forms';
import {RequestBookLoan} from '../../../custom.types';

@Component({
  selector: 'app-book-loan-form',
  imports: [
    FormsModule
  ],
  templateUrl: './book-loan-form.html',
  styleUrl: './book-loan-form.css',
})
export class BookLoanForm {
  constructor(//private readonly bookService: BookService,
    //private readonly userService: UserService,
    private readonly bookLoanService: BookLoanService,
    private readonly router: Router,
    private readonly route: ActivatedRoute) {
  }


  userId: string = '';
  bookId: string = '';
  isLoading: boolean = false;
  //editMode = false;


  /*getBook(bookId: string) {
    this.bookService.getBook(bookId)
  }*/

  insertLoan() {
    if (!this.bookId || !this.userId) {
      // Mostrar mensagem de erro ao usuário
      return;
    }

    this.isLoading = true;
    const request: RequestBookLoan = {bookId: this.bookId, userId: this.userId};

    this.bookLoanService.newLoan(request).subscribe({
      next: (response) => {
        this.isLoading = false;
        console.log(response);
        // Mostrar mensagem de sucesso
        this.router.navigate(['/emprestimos']);
      },
      error: (error) => {
        this.isLoading = false;
        console.log(error);
      }
    });
  }
}
