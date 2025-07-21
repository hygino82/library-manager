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
  ) {}

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
}
