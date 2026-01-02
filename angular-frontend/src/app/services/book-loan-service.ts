import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BASE_URL} from '../../utils';
import {Observable} from 'rxjs';
import {BookLoan, CreateLoanByEmailAndPersonalCodeRequest, RequestBookLoan, ResponseLoanPage} from '../../custom.types';

@Injectable({
  providedIn: 'root',
})
export class BookLoanService {
  constructor(private readonly http: HttpClient) {
  }

  getLoans(): Observable<ResponseLoanPage> {
    return this.http.get<ResponseLoanPage>(`${BASE_URL}/loan`);
  }

  createLoanByUserAndBookId(
    request: RequestBookLoan
  ): Observable<BookLoan> {
    return this.http.post<BookLoan>(
      `${BASE_URL}/loan`,
      request
    );
  }

  createLoanByEmailAndPersonalCode(
    request: CreateLoanByEmailAndPersonalCodeRequest
  ): Observable<BookLoan> {
    return this.http.post<BookLoan>(
      `${BASE_URL}/loan/optional`,
      request
    );
  }
}

