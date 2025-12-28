import { Routes } from '@angular/router';
import {BookList} from './page/book-list/book-list';
import {BookForm} from './page/book-form/book-form';
import {UserList} from './page/user-list/user-list';
import {UserForm} from './page/user-form/user-form';
import {BookLoanForm} from './page/book-loan-form/book-loan-form';
import {BookLoanList} from './page/book-loan-list/book-loan-list';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'livros',
    pathMatch: 'full'
  },

  /* Usuários */
  {
    path: 'usuarios',
    component: UserList
  },
  {
    path: 'usuarios/form',
    component: UserForm
  },
  {
    path: 'usuarios/form/:id',
    component: UserForm
  },

  /* Livros */
  {
    path: 'livros',
    component: BookList
  },
  {
    path: 'livros/usuario/:userId',
    component: BookList
  },
  {
    path: 'livros/cadastro',
    component: BookForm
  },
  {
    path: 'livros/cadastro/:id',
    component: BookForm
  },

   //Empréstimos
  {
    path: 'emprestimos',
    component: BookLoanList
  },
  {
    path: 'emprestimos/form',
    component: BookLoanForm
  },

  /* fallback */
  {
    path: '**',
    redirectTo: 'livros'
  }
];
