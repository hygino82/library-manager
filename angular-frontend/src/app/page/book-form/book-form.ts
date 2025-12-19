import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Button } from 'primeng/button';
import { ActivatedRoute, Router } from '@angular/router';
import { Book, BookRequest } from '../../../custom.types';
import { BookService } from '../../services/book.service';

@Component({
  selector: 'app-book-form',
  standalone: true,
  imports: [FormsModule, Button],
  templateUrl: './book-form.html',
  styleUrls: ['./book-form.css'],
})
export class BookForm implements OnInit {

  editMode = false;
  id?: string;

  title = '';
  author = '';
  personalCode = '';
  edition = 1;
  publisher = '';
  totalPages = 1;

  constructor(
    private readonly service: BookService,
    private readonly route: ActivatedRoute,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.editMode = true;
      this.findBook(id);
    }
  }

  // 🔎 Carrega dados para edição
  findBook(id: string): void {
    this.service.getBook(id).subscribe({
      next: (book: Book) => {
        this.id = book.id;
        this.title = book.title;
        this.author = book.author;
        this.personalCode = book.personalCode;
        this.edition = book.edition;
        this.publisher = book.publisher;
        this.totalPages = book.totalPages;
      },
      error: (err) => {
        console.error('Erro ao buscar livro', err);
      }
    });
  }

  // 📦 Monta o request (create / update)
  private buildRequest(): BookRequest {
    return {
      title: this.title,
      author: this.author,
      personalCode: this.personalCode,
      edition: this.edition,
      publisher: this.publisher,
      totalPages: this.totalPages
    };
  }

  // 💾 Salva (create OU update)
  saveBook(): void {
    const request = this.buildRequest();

    if (this.editMode && this.id) {
      this.updateBook(this.id, request);
    } else {
      this.createBook(request);
    }
  }

  // ➕ Create
  private createBook(request: BookRequest): void {
    this.service.saveBook(request).subscribe({
      next: (response) => {
        console.log('Livro criado com sucesso', response);
        this.router.navigate(['/livros']); // ajuste se necessário
      },
      error: (err) => {
        console.error('Erro ao criar livro', err);
      }
    });
  }

  // ✏️ Update
  private updateBook(id: string, request: BookRequest): void {
    this.service.updateBook(id, request).subscribe({
      next: () => {
        console.log('Livro atualizado com sucesso');
        this.router.navigate(['/livros']); // ajuste se necessário
      },
      error: (err) => {
        console.error('Erro ao atualizar livro', err);
      }
    });
  }
}
