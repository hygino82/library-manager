import {Component, OnInit} from '@angular/core';
import {MinUserResponse} from '../../../custom.types';
import {UserService} from '../../services/user-service';
import {TableModule} from 'primeng/table';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-user-list',
  imports: [TableModule, FormsModule],
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
          console.log(this.users);
        },
        error: err => console.error('Erro ao buscar usuários', err)
      });
  }

}
