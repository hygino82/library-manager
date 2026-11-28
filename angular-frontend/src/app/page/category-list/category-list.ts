import { NgClass } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { CategoryDto, CategoryPage } from '../../../custom.types';
import { CategoryService } from '../../services/category.service';



@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [FormsModule, TableModule, RouterLink, ButtonModule, TooltipModule, NgClass],
  templateUrl: './category-list.html',
  styleUrl: './category-list.css',
})
export class CategoryList implements OnInit {
  constructor(private readonly categoryService: CategoryService) { }

  ngOnInit(): void {
    this.getCategoryPage();
  }

  name: string = '';
  selectedCategory: CategoryDto | null = null;


  categoryPage: CategoryPage = {
    content: [],
    page: {
      size: 0,
      number: 0,
      totalPages: 0,
      totalElements: 0
    }
  }

  getCategoryPage() {
    this.categoryService.getCategories()
      .subscribe({
        next: (result) => {
          this.categoryPage = result;
          console.log(this.categoryPage.content);
        },
        error: (err) => {
          console.error('Erro ao carregar categorias', err);
        }
      });
  }

  getCategoryById(id: string) {
    console.log(`Category #${id}`);
  }

  removeCategoryById(id: string) {
    console.log(`Caregoria ${id} removida!`);
  }
}
