import { Component } from '@angular/core';
import { CategoryDto } from '../../../custom.types';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-category-list',
  imports: [FormsModule],
  templateUrl: './category-list.html',
  styleUrl: './category-list.css',
})
export class CategoryList {
  name: string = '';

  list: CategoryDto[] = [
    {
      id: 'e5b589fc-64a9-4959-b0e3-45eff646146d',
      description: 'Romances da Literatura Brasileira',
      name: 'Romance',
      createdAt: '2026-11-23T14:25:36',
      updatedAt: '2026-11-23T14:40:27',
    },
    {
      id: 'a12f8c31-7d42-4e91-9b63-2c8a5f71d004',
      description: 'Obras de ficção científica e exploração tecnológica',
      name: 'Ficção Científica',
      createdAt: '2026-11-23T15:10:00',
      updatedAt: '2026-11-23T15:10:00',
    },
    {
      id: 'b73d9e25-6f81-4a32-8c54-1d9f7b62e005',
      description: 'Narrativas de mistério, investigação e suspense',
      name: 'Suspense',
      createdAt: '2026-11-23T15:20:00',
      updatedAt: '2026-11-23T15:20:00',
    },
    {
      id: 'c84e1f36-5a92-4b43-9d65-2e0a8c73f006',
      description: 'Obras sobre acontecimentos históricos e personagens reais',
      name: 'História',
      createdAt: '2026-11-23T15:30:00',
      updatedAt: '2026-11-23T15:30:00',
    },
    {
      id: 'd95f2a47-6b03-4c54-8e76-3f1b9d84a007',
      description: 'Livros voltados ao desenvolvimento pessoal e profissional',
      name: 'Autoajuda',
      createdAt: '2026-11-23T15:40:00',
      updatedAt: '2026-11-23T15:40:00',
    },
    {
      id: 'e06a3b58-7c14-4d65-9f87-4a2c0e95b008',
      description: 'Obras de fantasia, magia e mundos imaginários',
      name: 'Fantasia',
      createdAt: '2026-11-23T15:50:00',
      updatedAt: '2026-11-23T15:50:00',
    },
    {
      id: 'f17b4c69-8d25-4e76-a098-5b3d1fa6c009',
      description: 'Livros sobre ciência, pesquisa e descobertas',
      name: 'Ciências',
      createdAt: '2026-11-23T16:00:00',
      updatedAt: '2026-11-23T16:00:00',
    },
    {
      id: 'a28c5d70-9e36-4f87-b109-6c4e2ab7d010',
      description: 'Narrativas biográficas e autobiográficas',
      name: 'Biografia',
      createdAt: '2026-11-23T16:10:00',
      updatedAt: '2026-11-23T16:10:00',
    },
    {
      id: 'b39d6e81-af47-4098-c210-7d5f3bc8e011',
      description: 'Obras literárias voltadas ao público infantil',
      name: 'Literatura Infantil',
      createdAt: '2026-11-23T16:20:00',
      updatedAt: '2026-11-23T16:20:00',
    },
    {
      id: 'c40e7f92-b058-41a9-d321-8e6a4cd9f012',
      description: 'Livros sobre programação, desenvolvimento e tecnologia',
      name: 'Tecnologia',
      createdAt: '2026-11-23T16:30:00',
      updatedAt: '2026-11-23T16:30:00',
    }
  ];

  getCategoryPage() {
    console.log('Testando');
  }

  getBookById(id:number){
    console.log(`Category #${id}`);
  }
}
