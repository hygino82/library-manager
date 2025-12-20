import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MinUserPage, RequestUserDto, User} from '../../custom.types';
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

  getUserById(id: string) {
    return this.http.get<User>(`${BASE_URL}/user/${id}`, {});
  }

  insertUser(requestUser: RequestUserDto) {
    return this.http.post<User>(`${BASE_URL}/user`, requestUser, {});
  }
}
