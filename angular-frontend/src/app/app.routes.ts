import { Routes } from '@angular/router';
import {BookList} from './page/book-list/book-list';
import {BookForm} from './page/book-form/book-form';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'livros',
    pathMatch: 'full'
  },

  /* Usuários */
  /*{
    path: 'usuarios',
    component: UsuarioListComponent
  },
  {
    path: 'usuarios/novo',
    component: UsuarioFormComponent
  },*/

  /* Livros */
  {
    path: 'livros',
    component: BookList
  },
  {
    path: 'livros/novo',
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
