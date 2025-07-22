import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookListComponent } from './page/book-list/book-list.component';
import { AuthorListComponent } from './page/author-list/author-list.component';

@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    AuthorListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
