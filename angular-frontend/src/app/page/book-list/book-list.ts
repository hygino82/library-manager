import {Component, OnInit} from '@angular/core';
import {ButtonModule} from 'primeng/button';
import {TableModule} from 'primeng/table';
import {BookService} from '../../services/book.service';
import {Book, RequestBookLoan} from '../../../custom.types';
import {FormsModule} from '@angular/forms';
import {Tooltip} from 'primeng/tooltip';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {BookLoanService} from '../../services/book-loan-service';

@Component({
  selector: 'app-book-list',
  imports: [TableModule, ButtonModule, FormsModule, Tooltip, RouterLink],
  templateUrl: './book-list.html',
  styleUrl: './book-list.css',
  standalone: true
})
export class BookList implements OnInit {

  userId: string = '';
  userIsValid = false;

  constructor(private readonly service: BookService,
              private readonly route: ActivatedRoute,
              private readonly bookLoanService: BookLoanService,
              private readonly router: Router) {
  }

  ngOnInit(): void {
    this.getBooks();

    const result = this.route.snapshot.paramMap.get('userId');

    if (!result) {
      throw new Error('userId não informado na rota');
    }

    this.userId = result;
    console.log(`userId : ${this.userId}`);

    this.userIsValid = true;
  }

  getBooks() {
    this.service.getBooks(this.title, this.author, this.status).subscribe({
      next: (result) => {
        this.books = result.content; // atualiza o array
        //console.log(this.books);
      },
      error: (err) => {
        console.error('Erro ao carregar livros', err);
      }
    });
    console.log(`BookStatus: ${this.status}`);
  }

  removeBook(id: string) {
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

  getBook(id: string) {
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

  borrowBook(bookId: string): void {
    if (!this.userId) {
      //console.error('Usuário inválido');
      this.router.navigate(['/usuarios/livro', bookId]);
      //return;
    } else {

      const loanRequest: RequestBookLoan = {
        bookId,
        userId: this.userId
      };

      this.bookLoanService.newLoan(loanRequest).subscribe({
        next: () => {
          console.log('Livro emprestado com sucesso');
          this.router.navigate(['/emprestimos']);
        },
        error: err => {
          console.error('Erro ao emprestar livro', err);
        }
      });
    }
  }

  selectedBook?: Book;  // A linha selecionada
  title: string = '';
  author: string = '';
  status: string = '';

  books: Book[] = [];
}


