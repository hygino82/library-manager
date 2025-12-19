import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MinUserPage} from '../../custom.types';
import {BASE_URL} from '../../utils';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {
  }

  getUsers(name: string): Observable<MinUserPage> {
    return this.http.get<MinUserPage>(`${BASE_URL}/user?name=${name}`, {});
  }
}
