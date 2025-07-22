import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Book } from './entities/book.entity';
import { Author } from 'src/author/entities/author.entity';
import { CreateBookDto } from './dto/create-book.dto';
import { ResponseBookDto } from './dto/response-book.dto';

@Injectable()
export class BookService {
  constructor(
    @InjectRepository(Book)
    private readonly bookRepository: Repository<Book>,
    @InjectRepository(Author)
    private readonly authorRepository: Repository<Author>,
  ) { }

  async createBook(createBookDto: CreateBookDto): Promise<ResponseBookDto> {
    const author = await this.authorRepository.findOneBy({ id: createBookDto.authorId });
    if (!author) {
      throw new Error('Autor não encontrado');
    }

    const book = new Book();
    book.title = createBookDto.title;
    book.publisher = createBookDto.publisher;
    book.edition = createBookDto.edition;
    book.author = author;
    const savedBook = await this.bookRepository.save(book);

    return new ResponseBookDto(savedBook);
  }

  async findAllBooks(): Promise<ResponseBookDto[]> {
    const books = await this.bookRepository.find({ relations: ['author'] });
    return books.map(book => new ResponseBookDto(book));
  }

  async findBookById(id: number): Promise<ResponseBookDto> {
    const book = await this.bookRepository.findOne({ where: { id }, relations: ['author'] });
    if (!book) {
      throw new Error('Livro não encontrado');
    }

    return new ResponseBookDto(book);
  }

  async updateBook(id: number, updateBookDto: CreateBookDto): Promise<ResponseBookDto> {
    const book = await this.bookRepository.findOne({ where: { id }, relations: ['author'] });
    if (!book) {
      throw new Error('Livro não encontrado');
    }

    const author = await this.authorRepository.findOneBy({ id: updateBookDto.authorId });
    if (!author) {
      throw new Error('Autor não encontrado');
    }

    book.title = updateBookDto.title;
    book.publisher = updateBookDto.publisher;
    book.edition = updateBookDto.edition;
    book.author = author;
    book.updatedAt = new Date();

    const updatedBook = await this.bookRepository.save(book);
    return new ResponseBookDto(updatedBook);
  }

  async removeBook(id: number): Promise<void> {
    const book = await this.bookRepository.findOneBy({ id });
    if (!book) {
      throw new Error('Livro não encontrado');
    }
    await this.bookRepository.remove(book);
  }
}
