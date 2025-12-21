import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Menubar} from 'primeng/menubar';
import {MenuItem, PrimeTemplate} from 'primeng/api';

@Component({
  selector: 'app-header',
  imports: [
    FormsModule,
    Menubar,
    PrimeTemplate
  ],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {
  items: MenuItem[] = [
    {
      label: 'Usuário',
      icon: 'pi pi-user',
      items: [
        {
          label: 'Cadastrar',
          icon: 'pi pi-plus',
          routerLink: '/usuarios/form'
        },
        {
          label: 'Listar',
          icon: 'pi pi-list',
          routerLink: '/usuarios'
        }
      ]
    },
    {
      label: 'Livro',
      icon: 'pi pi-book',
      items: [
        {
          label: 'Cadastrar',
          icon: 'pi pi-plus',
          routerLink: '/livros/cadastro'
        },
        {
          label: 'Listar',
          icon: 'pi pi-list',
          routerLink: '/livros'
        }
      ]
    },
    {
      label: 'Empréstimos',
      icon: 'pi pi-calendar',
      items: [
        {
          label: 'Cadastrar',
          icon: 'pi pi-plus',
          routerLink: '/emprestimos/form'
        },
        {
          label: 'Listar',
          icon: 'pi pi-list',
          routerLink: '/emprestimos'
        }
      ]
    }
  ];
}
          routerLink: '/emprestimos'
