import {Component, OnInit} from '@angular/core';
import {MinUserResponse, RequestBookLoan} from '../../../custom.types';
import {UserService} from '../../services/user-service';
import {TableModule} from 'primeng/table';
import {FormsModule} from '@angular/forms';
import {Button} from 'primeng/button';
import {Tooltip} from 'primeng/tooltip';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {BookLoanService} from '../../services/book-loan-service';

@Component({
  selector: 'app-user-list',
  imports: [TableModule, FormsModule, Button, Tooltip, RouterLink],
  templateUrl: './user-list.html',
  styleUrl: './user-list.css',
})
export class UserList implements OnInit {

  constructor(private readonly userService: UserService,
              private readonly router: Router,
              private readonly route: ActivatedRoute,
              private readonly bookLoanService: BookLoanService) {
  }

  ngOnInit(): void {
    this.getUsersPage();
    this.verifyBookId();
  }

  users: MinUserResponse[] = [];
  name: string = '';
  bookId: string = '';
  bookIsValid = false;

  verifyBookId() {
    const result = this.route.snapshot.paramMap.get('bookId');

    if (!result) {
      throw new Error('BookId não informado na rota');
    }

    this.bookId = result;
    console.log(`bookId : ${this.bookId}`);

    this.bookIsValid = true;
  }

  getUsersPage(): void {
    this.userService.getUsers(this.name)
      .subscribe({
        next: page => {
          this.users = page.content;
          //console.log(this.users);
        },
        error: err => console.error('Erro ao buscar usuários', err)
      });
  }

  getUserById(name: string): void {
    this.userService.getUsers(name).subscribe({})
  }

  removeUser(id: string): void {
    this.userService.deleteUser(id).subscribe({
      next: response => {
        console.log('Usuário removido com sucesso:', id);
        this.getUsersPage();
      },
      error: error => console.error('Erro ao remover usuário:', error)
    });
  }

  gotoBooklist(userId: string): void {
    this.router.navigate(['/livros/usuario', userId]);
  }

  borrowBook(userId: string): void {
    if (!this.bookId) {
      //console.error('Usuário inválido');
      this.gotoBooklist(userId);
      //return;
    } else {

      const loanRequest: RequestBookLoan = {
        bookId: this.bookId,
        userId
      };

      this.bookLoanService.newLoan(loanRequest).subscribe({
        next: () => {
          console.log('Livro emprestado com sucesso');
        },
        error: err => {
          console.error('Erro ao emprestar livro', err);
        }
      });
    }
  }
}
