import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BASE_URL} from '../../utils';
import {Book, BookPage, BookRequest} from '../../custom.types';


@Injectable({
  providedIn: 'root',
})
export class BookService {
  constructor(private http: HttpClient) {
  }

  getBooks(title: string, author: string, status: string): Observable<BookPage> {
    return this.http.get<BookPage>(`${BASE_URL}/book?title=${title}&author=${author}&bookStatus=${status}`, {});
  }

  removeBook(id: string) {
    return this.http.delete(`${BASE_URL}/book/${id}`);
  }

  getBook(id: string) {
    return this.http.get<Book>(`${BASE_URL}/book/${id}`);
  }

  saveBook(bookRequest: BookRequest) {
    return this.http.post(`${BASE_URL}/book`, bookRequest, {});
  }

  updateBook(id: string, bookRequest: BookRequest) {
    return this.http.put(`${BASE_URL}/book/${id}`, bookRequest, {});
  }
}
