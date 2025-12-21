import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Button} from 'primeng/button';
import {InputText} from 'primeng/inputtext';
import {Attribute, RequestUserDto, User} from '../../../custom.types';
import {UserService} from '../../services/user-service';
import {ActivatedRoute, Router} from '@angular/router';
import {SCHOOL_ATTRIBUTES} from '../../../utils';

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
export class UserForm implements OnInit {

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.editMode = true;
      this.findUser(id);
      this.id = id;
    }
  }

  id = '';
  name = '';
  email = '';
  schoolAttribute = 'SEXTO';
  phoneNumber = '';
  userResponse?: User;
  editMode: boolean = false;

  attributes: Attribute[] = SCHOOL_ATTRIBUTES;

  constructor(private readonly userService: UserService,
              private readonly route: ActivatedRoute,
              private readonly router: Router) {
  }

  saveUser(): void {
    const requestUser: RequestUserDto = {
      name: this.name,
      email: this.email,
      phoneNumber: this.phoneNumber,
      schoolAttribute: this.schoolAttribute
    };

    if (this.editMode) {
      this.userService.updateUser(this.id, requestUser).subscribe({
        next: response => {
         // alert('Usuário atualizado com sucesso: '+ response);
          this.router.navigate(['/usuarios']);
        },
        error: error => console.error('Erro ao atualizar usuário:', error)
      });
    } else {
      this.userService.insertUser(requestUser).subscribe({
        //next: response => alert('Usuário inserido com sucesso: '+ response.name),
        error: error => console.error('Erro ao inserir usuário:', error)
      });
      this.clearFields();
    }
  }

  findUser(id: string): void {
    this.userService.getUserById(id).subscribe({
      next: (response) => {
        if (response) {
          this.userResponse = response;
          //console.log(response);
          this.name = response.name;
          this.email = response.email;
          this.phoneNumber = response.phoneNumber;
          this.schoolAttribute = response.schoolAttribute;
        }
      },
      error: (err) => {
        console.error('Erro ao buscar usuário:', err);
      }
    });
  }

  clearFields() {
    this.name = '';
    this.email = '';
    this.phoneNumber = '';
    this.schoolAttribute = 'SEXTO';
  }
}
