import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BASE_URL} from '../../utils';
import {BookPage} from '../../custom.types';

@Injectable({
  providedIn: 'root',
})
export class BookService {
  constructor(private http: HttpClient) {}

  getBooks(title:string,author:string): Observable<BookPage> {
    return this.http.get<BookPage>(`${BASE_URL}/book?title=${title}&author=${author}`, {});
  }
}
