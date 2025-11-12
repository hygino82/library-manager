import {Component, OnInit} from '@angular/core';
import {ButtonModule} from 'primeng/button';
import {TableModule} from 'primeng/table';
import {App} from '../../app';
import {BookService} from '../../services/book.service';
import {Book} from '../../../custom.types';

@Component({
  selector: 'app-book-list',
  imports: [TableModule, ButtonModule],
  templateUrl: './book-list.html',
  styleUrl: './book-list.css',
})
export class BookList implements OnInit {

  constructor(private readonly service: BookService) {
  }

  ngOnInit(): void {
    this.service.getBooks(this.title, this.author).subscribe({
      next: (result) => {
        this.books = result.content; // acessa o conteúdo da página
        console.log(this.books);
      },
      error: (err) => {
        console.error('Erro ao carregar livros', err);
      }
    });
  }


  selectedBook?: Book;  // A linha selecionada
  title: string = '';
  author: string = '';

  books: Book[] = [];

}


