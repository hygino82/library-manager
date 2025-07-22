import { Component } from '@angular/core';
import { AuthorResponseDto } from 'src/types';

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent {
  authors: AuthorResponseDto[] = [
    {
      id: 1,
      name: 'J.K. Rowling',
      country: 'United Kingdom',
      createdAt: new Date('1997-06-26T00:00:00Z'),
      updatedAt: new Date('2023-10-01T00:00:00Z'),
      totalBooks: 7
    },
    {
      id: 2,
      name: 'George R.R. Martin',
      country: 'United States',
      createdAt: new Date('1996-08-06T00:00:00Z'),
      updatedAt: new Date('2023-10-01T00:00:00Z'),
      totalBooks: 5
    },
    {
      id: 3,
      name: 'J.R.R. Tolkien',
      country: 'United Kingdom',
      createdAt: new Date('1937-09-21T00:00:00Z'),
      updatedAt: new Date('2023-10-01T00:00:00Z'),
      totalBooks: 4
    },
    {
      id: 4,
      name: 'Agatha Christie',
      country: 'United Kingdom',
      createdAt: new Date('1920-01-01T00:00:00Z'),
      updatedAt: new Date('2023-10-01T00:00:00Z'),
      totalBooks: 66
    }
  ];

  deleteAuthor(authorId: number): void {
    this.authors = this.authors.filter(author => author.id !== authorId);
  }

  editAuthor(authorId: number): void {
    const author = this.authors.find(author => author.id === authorId);
    if (author) {
      // Logic to edit the author
      console.log(`Editing author: ${author.name}`);
    }
  }
}
