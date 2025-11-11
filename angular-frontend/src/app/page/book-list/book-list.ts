import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-book-list',
  imports: [TableModule,ButtonModule],
  templateUrl: './book-list.html',
  styleUrl: './book-list.css',
})
export class BookList {
  selectedBook?: Book;  // A linha selecionada

  books: Book[] = [
    {
      id: 1,
      title: 'The Great Gatsby',
      author: 'F. Scott Fitzgerald',
      personalCode: 'GG1925',
      edition: 1,
      publisher: 'Scribner',
      totalPages: 180,
      bookStatus: 'Available',
    },
    {
      id: 2,
      title: '1984',
      author: 'George Orwell',
      personalCode: 'N1984',
      edition: 1,
      publisher: 'Secker & Warburg',
      totalPages: 328,
      bookStatus: 'Checked Out',
    },
    {
      id: 3,
      title: 'To Kill a Mockingbird',
      author: 'Harper Lee',
      personalCode: 'TKM1960',
      edition: 1,
      publisher: 'J.B. Lippincott & Co.',
      totalPages: 281,
      bookStatus: 'Available',
    },
  ];
}

export type Book = {
  id: number;
  title: string;
  author: string;
  personalCode: string;
  edition: number;
  publisher: string;
  totalPages: number;
  bookStatus: string;
};
