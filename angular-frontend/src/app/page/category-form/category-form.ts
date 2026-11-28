import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CategoryDto, RequestCategoryDto } from '../../../custom.types';
import { CategoryService } from '../../services/category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { formatDate } from '../../../utils';

@Component({
  selector: 'app-category-form',
  imports: [FormsModule],
  templateUrl: './category-form.html',
  styleUrl: './category-form.css',
})
export class CategoryForm implements OnInit {
  id: string = '';
  private editMode = false;
  formatDate = formatDate;

  category: CategoryDto = {
    id: '',
    name: '',
    description: '',
    createdAt: '',
    updatedAt: ''
  }

  constructor(
    private readonly service: CategoryService,
    private readonly route: ActivatedRoute,
    private readonly router: Router) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.editMode = true;
      this.findCategory(id);
    }
  }



  save() {
    const { name, description } = this.category;

    const requestCategory: RequestCategoryDto = {
      name,
      description
    };

    console.log(requestCategory);

    if (!this.editMode) {
      const response = this.service.saveCategory(requestCategory);
      console.log(response);
    }

    else {
      const response = this.service.updateCategory(this.id, requestCategory);
      console.log(response);
    }
  }

  findCategory(id: string) {
    this.service.findCategory(id).subscribe({
      next: (category: CategoryDto) => {
        this.category = category
        console.log(category.id);
        /*this.id = category.id;
        this.name = category.name;
        this.description = category.description;
        this.createdAt = category.createdAt;
        this.updatedAt = category.updatedAt;*/
      },
      error: (err) => {
        console.error('Erro ao buscar categoria', err);
      }
    });
  }
}
