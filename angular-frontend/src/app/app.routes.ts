import { Routes } from '@angular/router';
import { BookForm } from './page/book-form/book-form';
import { BookList } from './page/book-list/book-list';
import { BookLoanForm } from './page/book-loan-form/book-loan-form';
import { BookLoanList } from './page/book-loan-list/book-loan-list';
import { CategoryForm } from './page/category-form/category-form';
import { CategoryList } from './page/category-list/category-list';
import { UserForm } from './page/user-form/user-form';
import { UserList } from './page/user-list/user-list';

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
    path: 'usuarios/livro/:bookId',
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
 /* {
    path: 'livros/usuario/:userId',
    component: BookList
  },*/
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
  {
    path: 'categorias/form',
    component: CategoryForm
  },
  {
    path: 'categorias/form/:id',
    component: CategoryForm
  },
  {
    path: 'categorias',
    component: CategoryList
  },

  /* fallback */
  {
    path: '**',
    redirectTo: 'categorias'
  }
];
