import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

import { BookLoanService } from '../../services/book-loan-service';
import {
  RequestBookLoan,
  CreateLoanByEmailAndPersonalCodeRequest
} from '../../../custom.types';

@Component({
  selector: 'app-book-loan-form',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './book-loan-form.html',
  styleUrl: './book-loan-form.css',
})
export class BookLoanForm {

  constructor(
    private readonly bookLoanService: BookLoanService,
    private readonly router: Router,
    private readonly route: ActivatedRoute
  ) {}

  loanType: 'ID' | 'CREDENTIALS' = 'ID';

  userId = '';
  bookId = '';

  email = '';
  personalCode = '';

  isLoading = false;

  insertLoan(): void {

    this.isLoading = true;

    /* ============================
       Fluxo usando ID do usuário
       ============================ */
    if (this.loanType === 'ID') {

      if (!this.userId || !this.bookId) {
        this.isLoading = false;
        return;
      }

      const request: RequestBookLoan = {
        userId: this.userId,
        bookId: this.bookId
      };

      this.bookLoanService
        .createLoanByUserAndBookId(request)
        .subscribe({
          next: () => {
            this.isLoading = false;
            this.router.navigate(['/emprestimos']);
          },
          error: (error) => {
            this.isLoading = false;
            console.error(error);
          }
        });

      return;
    }

    /* ============================
       Fluxo usando email e código
       ============================ */
    if (!this.email || !this.personalCode) {
      this.isLoading = false;
      return;
    }

    const request: CreateLoanByEmailAndPersonalCodeRequest = {
      email: this.email,
      personalCode: this.personalCode
    };

    this.bookLoanService
      .createLoanByEmailAndPersonalCode(request)
      .subscribe({
        next: () => {
          this.isLoading = false;
          this.router.navigate(['/emprestimos']);
        },
        error: (error) => {
          this.isLoading = false;
          console.error(error);
        }
      });
  }
}
