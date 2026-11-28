import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CategoryDto, CategoryPage, RequestCategoryDto } from '../../custom.types';
import { BASE_URL } from '../../utils';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private http: HttpClient) {
  }

  getCategories(): Observable<CategoryPage> {
    return this.http.get<CategoryPage>(`${BASE_URL}/category`, {});
  }

  async saveCategory(dto: RequestCategoryDto) {
    return await this.http.post<CategoryDto>(`${BASE_URL}/category`, dto, {});
  }

  findCategory(id: string) {
    return this.http.get<CategoryDto>(`${BASE_URL}/category/${id}`);
  }

  updateCategory(id: string, categoryRequest: RequestCategoryDto) {
    return this.http.put(`${BASE_URL}/category/${id}`, categoryRequest, {});
  }

  removeCategory(id: string) {
    return this.http.delete(`${BASE_URL}/category/${id}`);
  }
}
