import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RequestCategoryDto }  from '../../../custom.types';
@Component({
  selector: 'app-category-form',
  imports: [FormsModule],
  templateUrl: './category-form.html',
  styleUrl: './category-form.css',
})
export class CategoryForm {
  id:string|undefined='';
name:string='';
description:string='';

save(){
  const requestCategory:RequestCategoryDto={name:this.name,description:this.description};
  console.log(requestCategory);
}
}
