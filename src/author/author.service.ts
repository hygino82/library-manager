import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { CreateAuthorDto } from './dto/create-author.dto';
import { Author } from './entities/author.entity';

@Injectable()
export class AuthorService {
constructor(
    @InjectRepository(Author)
    private readonly authorRepository: Repository<Author>,
  ) {}

  async createAuthor(insertAuthor:CreateAuthorDto): Promise<Author> {
    const author = new Author();
    author.name = insertAuthor.name;
    author.country = insertAuthor.country;
    return await this.authorRepository.save(author);
  }

     
}


