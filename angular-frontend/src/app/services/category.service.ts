import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CategoryPage } from '../../custom.types';
import { BASE_URL } from '../../utils';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private http: HttpClient) {
  }

  async getCategories() {
    return await this.http.get<CategoryPage>(`${BASE_URL}/category`, {});
  }
}
