import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Button} from 'primeng/button';
import {BookRequest} from '../../../custom.types';
import {BookService} from '../../services/book.service';

@Component({
  selector: 'app-book-form',
  standalone: true,
  imports: [
    FormsModule,
    Button
  ],
  templateUrl: './book-form.html',
  styleUrls: ['./book-form.css'],
})
export class BookForm {
  constructor(private readonly service: BookService) {
  }

  title: string = '';
  author: string = '';
  personalCode: string = '';
  edition: number = 1;
  publisher: string = '';
  totalPages: number = 1;

  saveBook() {
    const bookRequest: BookRequest = {
      title: this.title,
      author: this.author,
      personalCode: this.personalCode,
      edition: this.edition,
      publisher: this.publisher,
      totalPages: this.totalPages
    };


    this.service.saveBook(bookRequest).subscribe({
      next: (response) => {
        console.log('Livro salvo com sucesso', response);
      },
      error: (err) => {
        console.error('Erro ao salvar livro', err);
      }
    });
  }
}

