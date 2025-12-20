import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Button} from 'primeng/button';
import {InputText} from 'primeng/inputtext';
import {RequestUserDto} from '../../../custom.types';
import {UserService} from '../../services/user-service';
import {NgFor} from '@angular/common';

@Component({
  selector: 'app-user-form',
  standalone: true,
  templateUrl: './user-form.html',
  styleUrls: ['./user-form.css'],
  imports: [
    FormsModule,
    InputText,
    Button
  ]
})
export class UserForm {
  name = '';
  email = '';
  schoolAttribute = 'SEXTO';
  phoneNumber = '';

  attributes = [
    { label: 'Sexto', value: 'SEXTO' },
    { label: 'Sétimo', value: 'SETIMO' },
    { label: 'Oitavo', value: 'OITAVO' },
    { label: 'Nono', value: 'NONO' },
    { label: 'Primeira', value: 'PRIMEIRA' },
    { label: 'Segunda', value: 'SEGUNDA' },
    { label: 'Terceira', value: 'TERCEIRA' },
    { label: 'Funcionário', value: 'FUNCIONARIO' },
    { label: 'Outro', value: 'OUTRO' }
  ];

  constructor(private readonly userService: UserService) {}

  insertUser(): void {
    const requestUser: RequestUserDto = {
      name: this.name,
      email: this.email,
      phoneNumber: this.phoneNumber,
      schoolAttribute: this.schoolAttribute
    };

    this.userService.insertUser(requestUser).subscribe({
      next: response => console.log('Usuário inserido com sucesso:', response),
      error: error => console.error('Erro ao inserir usuário:', error)
    });
  }
}
