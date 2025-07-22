import { Body, Controller, Get, Param, Post, Put, Delete } from '@nestjs/common';
import { BookService } from './book.service';
import { CreateBookDto } from './dto/create-book.dto';

@Controller('book')
export class BookController {
  constructor(private readonly bookService: BookService) { }

  @Post()
  create(@Body() createBookDto: CreateBookDto) {
    return this.bookService.createBook(createBookDto);
  }

  @Get()
  findAll() {
    return this.bookService.findAllBooks();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.bookService.findBookById(+id);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() updateBookDto: CreateBookDto) {
    return this.bookService.updateBook(+id, updateBookDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.bookService.removeBook(+id);
  }
}
