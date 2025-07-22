import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { CreateAuthorDto } from './dto/create-author.dto';
import { Author } from './entities/author.entity';
import { ResponseAutorDto } from './dto/response-autor.dto';
import { UpdateAuthorDto } from './dto/update-author.dto';

@Injectable()
export class AuthorService {
  constructor(
    @InjectRepository(Author)
    private readonly authorRepository: Repository<Author>,
  ) {}

  async createAuthor(insertAuthor: CreateAuthorDto): Promise<ResponseAutorDto> {
    const author = new Author();
    author.name = insertAuthor.name;
    author.country = insertAuthor.country;
    const res = await this.authorRepository.save(author);
    return new ResponseAutorDto(res);
  }

  async getAuthors(): Promise<ResponseAutorDto[]> {
    const authors = await this.authorRepository.find();
    return authors.map((author) => new ResponseAutorDto(author));
  }

  async findOne(id: number): Promise<ResponseAutorDto | null> {
    const author = await this.authorRepository.findOneBy({ id });
    return author ? new ResponseAutorDto(author) : null;
  }

  async update(
    id: number,
    updateAuthorDto: UpdateAuthorDto,
  ): Promise<ResponseAutorDto | null> {
    const author = await this.authorRepository.findOneBy({ id });

    if (!author) {
      return null;
    }

    author.name = updateAuthorDto.name;
    author.country = updateAuthorDto.country;
    author.updatedAt = new Date();
    const updatedAuthor = await this.authorRepository.save(author);

    return new ResponseAutorDto(updatedAuthor);
  }

  async remove(id: number): Promise<void> {
    await this.authorRepository.delete(id);
  }
}