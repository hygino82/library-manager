import { Routes } from '@angular/router';
import {BookList} from './page/book-list/book-list';
import {BookForm} from './page/book-form/book-form';
import {UserList} from './page/user-list/user-list';
import {UserForm} from './page/user-form/user-form';

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
    path: 'livros/cadastro',
    component: BookForm
  },
  {
    path: 'livros/cadastro/:id',
    component: BookForm
  },

  /* Empréstimos */
  /*{
    path: 'emprestimos',
    component: EmprestimoListComponent
  },
  {
    path: 'emprestimos/novo',
    component: EmprestimoFormComponent
  },*/

  /* fallback */
  {
    path: '**',
    redirectTo: 'livros'
  }
];
