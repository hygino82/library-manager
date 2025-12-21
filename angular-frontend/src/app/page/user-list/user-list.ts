import {Component, OnInit} from '@angular/core';
import {MinUserResponse} from '../../../custom.types';
import {UserService} from '../../services/user-service';
import {TableModule} from 'primeng/table';
import {FormsModule} from '@angular/forms';
import {Button} from 'primeng/button';
import {Tooltip} from 'primeng/tooltip';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-user-list',
  imports: [TableModule, FormsModule, Button, Tooltip, RouterLink],
  templateUrl: './user-list.html',
  styleUrl: './user-list.css',
})
export class UserList implements OnInit {

  constructor(private readonly userService: UserService) {
  }

  ngOnInit(): void {
    this.getUsersPage();
  }

  users: MinUserResponse[] = [];
  name: string = '';

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
}
