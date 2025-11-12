import {Component, signal} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {BookList} from "./page/book-list/book-list";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, BookList],
  templateUrl: './app.html',
  standalone: true,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular-frontend');
}
