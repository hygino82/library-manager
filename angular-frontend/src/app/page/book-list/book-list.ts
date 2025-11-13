import {Component, OnInit} from '@angular/core';
import {ButtonModule} from 'primeng/button';
import {TableModule} from 'primeng/table';
import {App} from '../../app';
import {BookService} from '../../services/book.service';
import {Book} from '../../../custom.types';
import {FormsModule} from '@angular/forms';
import {Tooltip} from 'primeng/tooltip';
import {NgIf} from '@angular/common';
import {Checkbox} from 'primeng/checkbox';

@Component({
  selector: 'app-book-list',
  imports: [TableModule, ButtonModule, FormsModule, Tooltip, Checkbox],
  templateUrl: './book-list.html',
  styleUrl: './book-list.css',
  standalone: true
})
export class BookList implements OnInit {

  constructor(private readonly service: BookService) {
  }

  ngOnInit(): void {
    this.getBooks();
  }

  getBooks() {
    this.service.getBooks(this.title, this.author).subscribe({
      next: (result) => {
        this.books = result.content; // atualiza o array
        console.log(this.books);
      },
      error: (err) => {
        console.error('Erro ao carregar livros', err);
      }
    });
  }

  removeBook(id: number) {
    this.service.removeBook(id).subscribe({
      next: () => {
        // A remoção foi bem-sucedida! Agora, vamos recarregar a lista.
        console.log(`Livro com ID ${id} removido com sucesso.`);
        this.getBooks();
      },
      error: (err) => {
        // Trate o erro, se a remoção falhar
        console.error(`Erro ao remover livro com ID ${id}:`, err);
        // Opcionalmente, você pode querer exibir uma mensagem para o usuário aqui
      }
    });
  }

  getBook(id: number) {
    this.service.getBook(id).subscribe({
      next: (book) => {
        this.selectedBook = book;
        console.log(this.selectedBook);
      },
      error: (err) => {
        console.error('Erro ao buscar o livro:', err);
      }
    });
  }

  borrowBook(book: Book) {
    console.log(`Livro ${book.title} emprestado!`);
  }

  showAvailableOnly = true;
  selectedBook?: Book;  // A linha selecionada
  title: string = '';
  author: string = '';

  books: Book[] = [];

}


